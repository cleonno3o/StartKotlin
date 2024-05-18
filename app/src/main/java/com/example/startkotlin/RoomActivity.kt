package com.example.startkotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

class RoomActivity : AppCompatActivity() {
    private val TAG = "RoomActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val myDatabase = Room.databaseBuilder(
            this,
            MyDatabase::class.java,
            "my_database"
        ).allowMainThreadQueries().build()

        findViewById<TextView>(R.id.save).setOnClickListener {
            val userProfile = UserProfile("주", "수민")
            myDatabase.userProfileDao().myInsert(userProfile)
        }

        findViewById<TextView>(R.id.load).setOnClickListener {
            val data = myDatabase.userProfileDao().getAll()
            data.forEach {
                Log.d(TAG, "id: ${it.id}, 내용: ${it.firstName}")
            }
        }
        findViewById<TextView>(R.id.delete).setOnClickListener {
            val data = myDatabase.userProfileDao().myDelete(1)
        }
    }
}

// Entity 구성
@Entity
class UserProfile(
    // 각 데이터를 구분할 고유한 값
    @PrimaryKey(autoGenerate = true) val id: Int,
    // entity는 일반적으로 snake_case를 사용
    // 이는 Kotlin의 Convention과 충돌한다
    // 실제 DB에는 snake_case로 저장되도록 지정할 수 있음
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "first_name")
    val firstName: String
) {
    constructor(lastName: String, firstName: String): this(0, lastName, firstName)
}

// DAO 구성
@Dao
interface UserProfileDao {

    // 데이터 추가
    // onConflict를 통해 같은 값이면 어떻게 할지 결정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun myInsert(userProfile: UserProfile)

    @Query("DELETE FROM userprofile WHERE id = :userId")
    fun myDelete(userId: Int)

    // SQL문과 동일하게 작성
    @Query("SELECT * FROM userprofile")
    fun getAll(): List<UserProfile>
}

// Database 구성, 데이터 베이스마다 별도로 구현
// entities: 관리할 엔티티 배열, 여러개가 올 수 있음
// version: 기존의 데이터베이스에 엔티티를 추가(Migration)하는 등 변경 시 사용하게 됨, 그때마다 새롭게 만들면 비효율적
@Database(entities = [UserProfile::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}
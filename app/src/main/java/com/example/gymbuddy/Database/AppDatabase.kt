package com.example.gymbuddy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*


@Database(entities = [User::class, Meal::class], version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val gender: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val goal: String,
    val level: String,
)

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE name = :name")
    fun getUserByName(name: String): LiveData<User>

    @Query("SELECT name FROM users")
    fun getUserNames(): LiveData<List<String>>

    @Query("SELECT id FROM users WHERE name = :name")
    fun getUserIdByName(name: String): LiveData<Int?>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): LiveData<User>

}

@Entity(tableName = "meals", foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])
])
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val name: String,
    val kcal: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val date: String
)

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: Meal)

    @Query("SELECT * FROM meals WHERE userId = :userId")
    fun getMealsByUserId(userId: Int): LiveData<List<Meal>>

    @Query("SELECT SUM(kcal) FROM meals WHERE userId = :userId AND date = :date")
    fun getTotalCaloriesForDay(userId: Int, date: String): LiveData<Int?>

    @Query("SELECT * FROM meals WHERE userId = :userId AND date = :date")
    fun getMealsByUserIdAndDate(userId: Int, date: String): LiveData<List<Meal>>

    @Query("SELECT SUM(protein) FROM meals WHERE userId = :userId AND date = :date")
    fun getTotalProteinForDay(userId: Int, date: String): LiveData<Int?>

    @Query("SELECT SUM(carbs) FROM meals WHERE userId = :userId AND date = :date")
    fun getTotalCarbsForDay(userId: Int, date: String): LiveData<Int?>

    @Query("SELECT SUM(fat) FROM meals WHERE userId = :userId AND date = :date")
    fun getTotalFatForDay(userId: Int, date: String): LiveData<Int?>

}
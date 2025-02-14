package com.tba.todox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tba.todox.core.model.Tag
import com.tba.todox.core.model.Task



//@Dao
//interface TaskDao {
//    @Insert
//    suspend fun insert(task: Task)
//
//    @Query("SELECT * FROM tasks INNER JOIN tags ON tasks.tagId = tags.id ")
//    suspend fun getAllTasksWithTags():List<Task>
//}
//
//@Dao
//interface TagDao {
//    @Insert
//    suspend fun insert(tag: Tag)
//
//    @Query("")
//    suspend fun getAllTags():List<Tag>
//}
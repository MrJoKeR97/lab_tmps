package repository

import com.example.taskmanager.Task

// 2. Open/Closed Principle (OCP)
interface TaskRepository {
    fun addTask(task: Task)
    abstract fun removeTask(task: Task)
    fun getTasks(): List<Task>
}


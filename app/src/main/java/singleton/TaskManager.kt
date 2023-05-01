package singleton

import com.example.taskmanager.Task
import repository.TaskRepository

// 3. Liskov Substitution Principle (LSP)
class TaskManager private constructor(private val taskRepository: TaskRepository) {
    fun addTask(task: Task) {
        taskRepository.addTask(task)
    }

    fun removeTask(task: Task) {
        taskRepository.removeTask(task)
    }

    fun completeTask(task: Task) {
        task.completeTask()
    }

    fun getTasks(): List<Task> {
        return taskRepository.getTasks()
    }

    companion object {
        @Volatile
        private var INSTANCE: TaskManager? = null

        fun getInstance(taskRepository: TaskRepository): TaskManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: TaskManager(taskRepository).also { INSTANCE = it }
            }
        }
    }
}

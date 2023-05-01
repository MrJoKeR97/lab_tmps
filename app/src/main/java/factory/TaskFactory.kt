package factory

import com.example.taskmanager.Task

interface TaskFactory {
    fun createTask(id: Int, name: String): Task
}
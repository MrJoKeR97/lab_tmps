package builder

import com.example.taskmanager.Task

// builder/TaskBuilder.kt
interface TaskBuilder {
    fun setId(id: Int): TaskBuilder
    fun setName(name: String): TaskBuilder
    fun build(): Task
}


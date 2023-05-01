package repository

import com.example.taskmanager.Task

class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf<Task>()

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun removeTask(task: Task) {
        tasks.remove(task)
    }

    override fun getTasks(): List<Task> {
        return tasks.toList()
    }

}

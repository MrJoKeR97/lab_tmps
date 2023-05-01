package prototype

import com.example.taskmanager.Task

class TaskPrototype(private val task: Task) : Task {
    override val id: Int
        get() = task.id

    override val name: String
        get() = task.name

    override val isCompleted: Boolean
        get() = task.isCompleted

    override fun completeTask() {
        task.completeTask()
    }

    fun clone(): Task {
        return TaskPrototype(task)
    }
}

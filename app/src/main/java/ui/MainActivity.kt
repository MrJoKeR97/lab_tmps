package ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import builder.SimpleTaskBuilder
import builder.TaskBuilder
import com.example.task_app.R
import factory.SimpleTaskFactory
import factory.TaskFactory
import notification.SimpleNotifier
import notification.TaskNotificationService
import prototype.TaskPrototype
import repository.InMemoryTaskRepository
import singleton.TaskManager


class MainActivity : AppCompatActivity() {
    private lateinit var taskManager: TaskManager
    private lateinit var taskNotificationService: TaskNotificationService
    private lateinit var taskFactory: TaskFactory
    private lateinit var taskBuilder: TaskBuilder
    private lateinit var taskListTextView: TextView
    private lateinit var taskNameInput: EditText
    private lateinit var addTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskListTextView = findViewById(R.id.task_list_text_view)
        taskNameInput = findViewById(R.id.task_name_input)
        addTaskButton = findViewById(R.id.add_task_button)
        val removeTaskButton = findViewById<Button>(R.id.delete_task_button)
        removeTaskButton.setOnClickListener {
            val taskId = taskNameInput.text.toString().trim().toIntOrNull()
            if (taskId != null) {
                val taskToRemove = taskManager.removeTask(taskId)
                if (taskToRemove != null) {
                    printAllTasks()
                    taskNameInput.text.clear()
                } else {
                    Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a valid task ID", Toast.LENGTH_SHORT).show()
            }
        }

        // Initialize TaskManager, TaskNotificationService, TaskFactory, and TaskBuilder
        val taskRepository = InMemoryTaskRepository()
        val notifiable = SimpleNotifier()
        taskManager = TaskManager.getInstance(taskRepository)
        taskNotificationService = TaskNotificationService(notifiable)
        taskFactory = SimpleTaskFactory()
        taskBuilder = SimpleTaskBuilder()

        // Use TaskFactory to create a task
        val factoryTask = taskFactory.createTask(id = 1, name = "Factory Task")
        taskManager.addTask(factoryTask)

        // Use TaskBuilder to create a task
        val builderTask = taskBuilder.setId(2).setName("Builder Task").build()
        taskManager.addTask(builderTask)

        // Use TaskPrototype to create a clone of a task
        val prototypeTask = TaskPrototype(factoryTask)
        val clonedTask = prototypeTask.clone()
        taskManager.addTask(clonedTask)

        // Complete a task and send a notification
        taskManager.completeTask(factoryTask)
        taskNotificationService.notifyTaskCompletion(factoryTask)

        // Remove a task created using TaskBuilder
        //taskManager.removeTask(taskId)

        // Print all tasks
        printAllTasks()

        // Add a task when button is clicked
        addTaskButton.setOnClickListener {
            val taskName = taskNameInput.text.toString()
            if (taskName.isNotEmpty()) {
                val newTask = taskFactory.createTask(id = taskManager.getTasks().size + 1, name = taskName)
                taskManager.addTask(newTask)
                printAllTasks()
                taskNameInput.text.clear()
            }
        }
    }

    private fun printAllTasks() {
        val tasks = taskManager.getTasks()
        val sb = StringBuilder()
        tasks.forEach { task ->
            val status = if (task.isCompleted) "completed" else "not completed"
            sb.append("Task '${task.name}' (ID: ${task.id}) is $status\n")
        }
        taskListTextView.text = sb.toString()
    }
}



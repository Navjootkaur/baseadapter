package com.navjoot.baseadpater

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.navjoot.baseadpater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var baseAdapterClass: BaseAdapterClass
    lateinit var binding: ActivityMainBinding
    var studentList = mutableListOf<StudentDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        baseAdapterClass = BaseAdapterClass(studentList)
        binding.listview.adapter = baseAdapterClass
        baseAdapterClass = BaseAdapterClass(studentList)
        binding.listview.adapter = baseAdapterClass
        studentList.add(StudentDataClass(1, "A"))
        studentList.add(StudentDataClass(2, "B"))
        studentList.add(StudentDataClass(name = "C"))
        binding.listview.setOnItemClickListener { adapter, view, position, id ->
            studentList.removeAt(position)
            baseAdapterClass.notifyDataSetChanged()
        }
          binding.fab.setOnClickListener {
                var dialog = Dialog(this)
                dialog.setContentView(R.layout.add_dialog)
                dialog.show()
              dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                var etId = dialog.findViewById<EditText>(R.id.etId)
                var etName = dialog.findViewById<EditText>(R.id.etName)
                var btnAdd = dialog.findViewById<Button>(R.id.btnAdd)
                btnAdd.setOnClickListener {
                    if (etName.text.toString().isNullOrEmpty())
                        etName.error = "Enter name"
                    else if (etId.text.toString().isNullOrEmpty())
                        etName.error = "Enter id"
                    else {
                        studentList.add(StudentDataClass( etId.text.toString().toLong(),etName.text.toString()))
                        baseAdapterClass.notifyDataSetChanged()
                        dialog.dismiss()
                    }

                }


            }
        }
    }

package com.example.IntelliGrader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.IntelliGrader.CourseAdapter
import com.example.IntelliGrader.CourseRepository
import com.example.IntelliGrader.R
import com.example.IntelliGrader.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var courseRepository: CourseRepository
    private lateinit var adapter: CourseAdapter // You need to create an adapter for your RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        courseRepository = CourseRepository(requireContext())
        adapter = CourseAdapter() // You need to create this adapter
    }
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Set up RecyclerView with the adapter
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set up the FAB click listener
        val fab: FloatingActionButton = root.findViewById(R.id.btnAdd)
        fab.setOnClickListener {
            // Handle FAB click, for example, show a dialog to add a new course
        }

        // Load and display courses
        val courses = courseRepository.getAllCourses()
        adapter.submitList(courses)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

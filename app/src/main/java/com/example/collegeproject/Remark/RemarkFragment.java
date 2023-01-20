package com.example.collegeproject.Remark;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.collegeproject.HomeActivity;
import com.example.collegeproject.R;
import com.example.collegeproject.attendance.AttendanceClassAdapter;
import com.example.collegeproject.attendance.AttendanceModelClass;
import com.example.collegeproject.databinding.FragmentRemarkBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemarkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemarkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemarkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemarkFragment newInstance(String param1, String param2) {
        RemarkFragment fragment = new RemarkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentRemarkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRemarkBinding.inflate(inflater,container,false);

        List<AttendanceModelClass> classList = new ArrayList<>();

        classList.add(new AttendanceModelClass("CSE 2nd Year","Section : A", R.drawable.logo));
        classList.add(new AttendanceModelClass("CSE 2nd Year","Section : B", R.drawable.logo));
        classList.add(new AttendanceModelClass("CSE 3rd Year","Section : A", R.drawable.logo));
        classList.add(new AttendanceModelClass("CSE 4th Year","Section : A", R.drawable.logo));

        AttendanceClassAdapter adapter = new AttendanceClassAdapter(getContext(),R.layout.single_row_attendance_class,classList);
        binding.classListView.setAdapter(adapter);

        binding.classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(getContext(),RemarkActivity.class));
            }
        });




        return binding.getRoot();
    }
}
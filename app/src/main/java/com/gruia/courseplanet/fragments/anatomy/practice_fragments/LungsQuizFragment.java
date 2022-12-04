package com.gruia.courseplanet.fragments.anatomy.practice_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gruia.courseplanet.R;
import com.gruia.courseplanet.database.ProgressDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LungsQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LungsQuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProgressDAO progressDAO = ProgressDAO.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LungsQuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LungsQuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LungsQuizFragment newInstance(String param1, String param2) {
        LungsQuizFragment fragment = new LungsQuizFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_lungs_quiz, container, false);
        // Inflate the layout for this fragment
        RadioButton answer1 = (RadioButton) rootview.findViewById(R.id.lungsQuizAnswer1);
        RadioButton answer2 = (RadioButton) rootview.findViewById(R.id.lungsQuizAnswer2);
        RadioButton answer3 = (RadioButton) rootview.findViewById(R.id.lungsQuizAnswer3);
        RadioButton answer4 = (RadioButton) rootview.findViewById(R.id.lungsQuizAnswer4);

        Button lungsFinishButton = (Button) rootview.findViewById(R.id.lungsQuizButton);

        lungsFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer1.isChecked() && answer2.isChecked() && answer3.isChecked()
                        && answer4.isChecked())
                {
                    progressDAO.progress();
                    Toast.makeText(rootview.getContext(),"ALL ANSWERS CORRECT",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(rootview.getContext(),"SOMETHING WAS NOT CORRECT",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootview;
    }
}
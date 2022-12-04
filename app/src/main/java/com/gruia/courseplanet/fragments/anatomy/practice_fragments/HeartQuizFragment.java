package com.gruia.courseplanet.fragments.anatomy.practice_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.gruia.courseplanet.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeartQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeartQuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HeartQuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartQuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartQuizFragment newInstance(String param1, String param2) {
        HeartQuizFragment fragment = new HeartQuizFragment();
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
        View rootview = inflater.inflate(R.layout.fragment_heart_quiz, container, false);
        // Inflate the layout for this fragment
        RadioButton answer1 = (RadioButton) rootview.findViewById(R.id.heartQuizAnswer1);
        RadioButton answer2 = (RadioButton) rootview.findViewById(R.id.heartQuizAnswer2);
        RadioButton answer3 = (RadioButton) rootview.findViewById(R.id.heartQuizAnswer3);
        RadioButton answer4 = (RadioButton) rootview.findViewById(R.id.heartQuizAnswer4);

        Button heartFinishButton = (Button) rootview.findViewById(R.id.heartQuizButton);

        heartFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer1.isChecked() && answer2.isChecked() && answer3.isChecked()
                        && answer4.isChecked())
                {
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                    System.out.println("MERGE TOATE BUNE E BINE TE PUP");
                }
                else
                {
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                    System.out.println("UN RASPUNS N-A FOST BUN SA MORA MASA");
                }
            }
        });
        return rootview;
    }
}
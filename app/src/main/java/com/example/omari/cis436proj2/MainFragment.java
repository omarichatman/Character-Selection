package com.example.omari.cis436proj2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import org.jetbrains.annotations.Nullable;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_selection, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View placeholder = getActivity().findViewById(R.id.points_fragment);
                Fragment character = null;

                if(placeholder == null) { // small screen
                    switch(checkedId)
                    {
                        case R.id.warriorBtn:
                            Intent intent = new Intent(getActivity(), SecondaryActivity.class);
                            intent.putExtra("classSelected", "warrior");
                            startActivity(intent);
                            break;
                        case R.id.mageBtn:
                            Intent mage = new Intent(getActivity(), SecondaryActivity.class);
                            mage.putExtra("classSelected", "mage");
                            startActivity(mage);
                            break;
                        case R.id.healerBtn:
                            Intent healer = new Intent(getActivity(), SecondaryActivity.class);
                            healer.putExtra("classSelected", "healer");
                            startActivity(healer);
                            break;
                        case R.id.hunterBtn:
                            Intent hunter = new Intent(getActivity(), SecondaryActivity.class);
                            hunter.putExtra("classSelected", "hunter");
                            startActivity(hunter);
                            break;
                        case R.id.paladinBtn:
                            Intent paladin = new Intent(getActivity(), SecondaryActivity.class);
                            paladin.putExtra("classSelected", "paladin");
                            startActivity(paladin);
                            break;
                    }
                }
                else
                { // big screen
                    switch(checkedId) {
                        case R.id.warriorBtn:
                            character = new warriorFragment();
                            break;
                        case R.id.mageBtn:
                            character = new mageFragment();
                            break;
                        case R.id.healerBtn:
                            character = new healerFragment();
                            break;
                        case R.id.hunterBtn:
                            character = new hunterFragment();
                            break;
                        case R.id.paladinBtn:
                            character = new paladinFragment();
                            break;
                    }
                    if(character != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.points_fragment, character);
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }
}


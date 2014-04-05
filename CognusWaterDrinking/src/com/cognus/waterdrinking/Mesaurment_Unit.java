package com.cognus.waterdrinking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.espian.showcaseview.ShowcaseView;
import com.espian.showcaseview.targets.ViewTarget;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Mesaurment_Unit extends Fragment {

	RadioGroup weight_unit;
	RadioGroup capacity_unit;
	SharedPreferences sharedpreferences;
	Editor editor;
	Button next, back;

	public Mesaurment_Unit() {
		// Required empty public constructor
	}

	ShowcaseView sv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View inflate = inflater.inflate(R.layout.fragment_mesaurment__unit,
				container, false);
		weight_unit = (RadioGroup) inflate.findViewById(R.id.type_female);
		capacity_unit = (RadioGroup) inflate.findViewById(R.id.RadioGroup01);

		sharedpreferences = getActivity().getSharedPreferences(
				Utills.MyPREFERENCES, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();
		ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
		co.hideOnClickOutside = true;
		sv = ShowcaseView.insertShowcaseView(new ViewTarget(
				R.id.RadioGroup01, getActivity()), getActivity(),
				R.string.showcase_welcome, R.string.showcase_weight, co);
		sv.show();
		if (!(sharedpreferences.contains(Utills.CAPWEIGHTID))) {

			editor.putInt(Utills.KEYWEIGHTID, Utills.KGID);
			editor.putInt(Utills.CAPWEIGHTID, Utills.MLID);
			editor.commit();
	
		} else {

			if (sharedpreferences.getInt(Utills.KEYWEIGHTID, Utills.KGID) == Utills.KGID) {
				weight_unit.check(R.id.pregnant);
			} else {
				weight_unit.check(R.id.breastfeeding);

			}

			if (sharedpreferences.getInt(Utills.CAPWEIGHTID, Utills.MLID) == Utills.MLID) {
				capacity_unit.check(R.id.RadioButton02);
			} else {
				capacity_unit.check(R.id.RadioButton01);

			}
		}

		weight_unit.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.pregnant: {
					editor.putInt(Utills.KEYWEIGHTID, Utills.KGID);
					editor.commit();
					break;
				}
				case R.id.breastfeeding: {
					editor.putInt(Utills.KEYWEIGHTID, Utills.LBSID);
					editor.commit();
					break;
				}
				}

			}
		});

		capacity_unit.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.RadioButton02: {
					editor.putInt(Utills.CAPWEIGHTID, Utills.MLID);
					editor.commit();
					break;
				}
				case R.id.RadioButton01: {
					editor.putInt(Utills.CAPWEIGHTID, Utills.OZID);
					editor.commit();
					break;
				}

				}

			}
		});
		next = (Button) inflate.findViewById(R.id.button2);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).ReplaceFragment(PersonalDetails
						.instantiate(getActivity(),
								PersonalDetails.class.getName()));
			}
		});

		back = (Button) inflate.findViewById(R.id.button1);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});

		return inflate;

	}

}

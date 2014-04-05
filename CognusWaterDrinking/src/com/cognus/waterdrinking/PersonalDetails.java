package com.cognus.waterdrinking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class PersonalDetails extends Fragment {

	public PersonalDetails() {
		// Required empty public constructor
	}

	EditText calculated_water, weight;
	ImageView gender_view;
	Spinner life_Style_spin;
	Spinner wether_spin;
	Button next;
	Button cancel;
	SharedPreferences sharedpreferences;
	Editor editor;
	TextView capacity_sign;
	TextView weight_sign;
	View gender_type;
	CheckBox is_pregnent;
	CheckBox is_breastfeeding;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View inflate = inflater.inflate(R.layout.fragment_personal_details,
				container, false);
		calculated_water = (EditText) inflate.findViewById(R.id.calculated_val);
		weight = (EditText) inflate.findViewById(R.id.weight);
		calculated_water.setKeyListener(null);
		gender_view = (ImageView) inflate.findViewById(R.id.gender_view);
		life_Style_spin = (Spinner) inflate.findViewById(R.id.life_style);
		wether_spin = (Spinner) inflate.findViewById(R.id.weather);
		next = (Button) inflate.findViewById(R.id.button2);
		cancel = (Button) inflate.findViewById(R.id.button1);
		sharedpreferences = getActivity().getSharedPreferences(
				Utills.MyPREFERENCES, Context.MODE_PRIVATE);

		capacity_sign = (TextView) inflate.findViewById(R.id.weight_id);
		weight_sign = (TextView) inflate.findViewById(R.id.weightid1);

		is_pregnent = (CheckBox) inflate.findViewById(R.id.pregnant);
		is_breastfeeding = (CheckBox) inflate.findViewById(R.id.breastfeeding);
		gender_type = inflate.findViewById(R.id.gender_type);

		if (sharedpreferences.contains(Utills.CAPWEIGHTID)) {

			if (sharedpreferences.getInt(Utills.CAPWEIGHTID, Utills.MLID) == Utills.MLID) {
				capacity_sign.setText(R.string.ml);

			} else {
				capacity_sign.setText(R.string.oz);

			}

			if (sharedpreferences.getInt(Utills.KEYWEIGHTID, Utills.KGID) == Utills.KGID) {
				weight_sign.setText(R.string.kg);
			} else {
				weight_sign.setText(R.string.lbs);
			}
		}
		if (sharedpreferences.contains(Utills.KEYWEIGHTVAL)) {
			try {
				if (sharedpreferences.getInt(Utills.KEYWEIGHTID, Utills.LBSID) == Utills.LBSID) {
					Double kgtoLbs = Utills.kgtoLbs(Utills.numFormat.parse(
							sharedpreferences.getString(Utills.KEYWEIGHTVAL,
									0 + "")).doubleValue());
					weight.setText(Utills.numFormat.format(kgtoLbs));
				} else {
					weight.setText(Utills.numFormat.format(Utills.numFormat
							.parse(sharedpreferences.getString(
									Utills.KEYWEIGHTVAL, 0 + "")).doubleValue()));

				}
				if (sharedpreferences.getInt(Utills.KEYGENDERVAL,
						R.drawable.ic_male) == R.drawable.ic_male) {
					gender_view.setTag(R.drawable.ic_male);
					gender_view.setImageResource(R.drawable.ic_male);
					gender_type.setVisibility(View.GONE);
					is_pregnent.setChecked(false);
					is_breastfeeding.setChecked(false);

				} else {
					gender_view.setTag(R.drawable.ic_female);
					gender_view.setImageResource(R.drawable.ic_female);
					gender_type.setVisibility(View.VISIBLE);
				}

				is_pregnent.setChecked(sharedpreferences.getBoolean(
						Utills.KEYISPREGNET, false));
				is_breastfeeding.setChecked(sharedpreferences.getBoolean(
						Utills.KEYISBREASTFEEDING, false));

				life_Style_spin.setSelection(sharedpreferences.getInt(
						Utills.KEYLIFESTYLEVAL, 0));

				wether_spin.setSelection(sharedpreferences.getInt(
						Utills.KEYWEATHERVAL, 0));

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		gender_view.setTag(R.drawable.ic_male);
		gender_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int tag = (Integer) v.getTag();
				if (tag == R.drawable.ic_female) {

					gender_view.setImageResource(R.drawable.ic_male);
					gender_view.setTag(R.drawable.ic_male);
					gender_type.setVisibility(View.GONE);
					is_pregnent.setChecked(false);
					is_pregnent.setChecked(false);
				} else {

					gender_view.setImageResource(R.drawable.ic_female);
					gender_view.setTag(R.drawable.ic_female);
					gender_type.setVisibility(View.VISIBLE);
				}
				weight.setText(weight.getText().toString());

			}
		});
		is_pregnent.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					is_breastfeeding.setChecked(false);
				}
				weight.setText(weight.getText().toString());

			}
		});

		is_breastfeeding
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							is_pregnent.setChecked(false);
						}
						weight.setText(weight.getText().toString());

					}
				});

		life_Style_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				weight.setText(weight.getText().toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		wether_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				weight.setText(weight.getText().toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		weight.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				calculated_water.setText(calculateWaterLevel());
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).ReplaceFragment(PersonalDetails
						.instantiate(getActivity(),
								Mesaurment_Unit.class.getName()));
			}
		});
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				((MainActivity) getActivity()).ReplaceFragment(ContainerBuilder
						.newInstance(null));
			}
		});

		return inflate;
	}

	private String calculateWaterLevel() {
		Double neededml = 0.00;
		if (weight.getText().length() > 0) {
			try {

				editor = sharedpreferences.edit();
				editor.putString(Utills.KEYNEEDEDML,
						Utills.numFormat.format(neededml));
				editor.commit();
				Double weigh = Utills.numFormat.parse(
						weight.getText().toString()).doubleValue();
				if (sharedpreferences.getInt(Utills.KEYWEIGHTID, Utills.LBSID) == Utills.LBSID) {
					weigh = Utills.lbstoKg(weigh);
				}
				editor.putString(Utills.KEYWEIGHTVAL,
						Utills.numFormat.format(weigh));
				int gender = (Integer) gender_view.getTag();
				Double neededmlperkg = 33.3330989;
				editor.putInt(Utills.KEYGENDERVAL, R.drawable.ic_male);
				if (gender == R.drawable.ic_female) {
					editor.putInt(Utills.KEYGENDERVAL, R.drawable.ic_female);

					neededmlperkg = 29.34534;
					if (is_pregnent.isChecked()) {
						neededmlperkg = 780.00;

					} else if (is_breastfeeding.isChecked()) {
						neededmlperkg = 730.00;

					}

				}
				editor.putBoolean(Utills.KEYISBREASTFEEDING,
						is_breastfeeding.isChecked());
				editor.putBoolean(Utills.KEYISPREGNET, is_pregnent.isChecked());
				editor.putInt(Utills.KEYLIFESTYLEVAL,
						life_Style_spin.getSelectedItemPosition());
				if (life_Style_spin.getSelectedItemPosition() == 1) {
					neededmlperkg += 6;

				}
				neededml = weigh * neededmlperkg;
				if (wether_spin.getSelectedItemPosition() == 1) {
					neededml += 485;

				}

				editor.putString(Utills.KEYNEEDEDML,
						Utills.numFormat.format(neededml));
				editor.putInt(Utills.KEYWEATHERVAL,
						wether_spin.getSelectedItemPosition());
				editor.commit();

				if (sharedpreferences.getInt(Utills.CAPWEIGHTID, Utills.MLID) != Utills.MLID) {

					neededml = Utills.mltoOz(neededml);
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		return Utills.numFormat.format(neededml);
	}
}

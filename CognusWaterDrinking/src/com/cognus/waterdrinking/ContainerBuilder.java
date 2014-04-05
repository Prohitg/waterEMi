package com.cognus.waterdrinking;

import java.text.ParseException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cognus.model.Container;
import com.jensdriller.libs.undobar.UndoBar;
import com.jensdriller.libs.undobar.UndoBar.Listener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ContainerBuilder#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class ContainerBuilder extends Fragment {

	public int[] drawarray = { R.drawable.drinkware_0, R.drawable.drinkware_1,
			R.drawable.drinkware_2, R.drawable.drinkware_3,
			R.drawable.drinkware_4, R.drawable.drinkware_5,
			R.drawable.drinkware_6, R.drawable.drinkware_7 };

	public double capacityarray[] = { 200.0, 180.0, 130.0, 250.0, 330.00,
			200.00, 500.00, 300.00 };

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param args
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ContainerBuilder.
	 */
	// TODO: Rename and change types and number of parameters
	public static ContainerBuilder newInstance(Bundle args) {
		ContainerBuilder fragment = new ContainerBuilder();

		fragment.setArguments(args);
		return fragment;
	}

	public ContainerBuilder() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	GridView grid;
	ContainerAdapter adapter;
	SharedPreferences sharedpreferences;
	AlertDialog changeVal;
	Button back, next;
	ImageView add_new_Container;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup vg,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View inflate = inflater.inflate(R.layout.fragment_container_builder,
				vg, false);
		sharedpreferences = getActivity().getSharedPreferences(
				Utills.MyPREFERENCES, Context.MODE_PRIVATE);
		Container container = Container.getContainer(getActivity(), 8 + "");
		if (container == null) {
			int j = 1;
			for (int i = 0; i < drawarray.length; i++) {
				Container contain = new Container(j++, capacityarray[i], 0,
						drawarray[i]);
				j++;
				contain.insert(getActivity());
			}

		}

		adapter = new ContainerAdapter();
		grid = (GridView) inflate.findViewById(R.id.gridView1);
		next = (Button) inflate.findViewById(R.id.button2);
		back = (Button) inflate.findViewById(R.id.button1);
		add_new_Container = (ImageView) inflate.findViewById(R.id.icon_dialoge);
		grid.setAdapter(adapter);

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View customTitleView = inflater.inflate(R.layout.dialoge_title,
						null);
				TextView text = (TextView) customTitleView
						.findViewById(R.id.title_dialoge);
				ImageView icon = (ImageView) customTitleView
						.findViewById(R.id.icon_dialoge);

				text.setText(R.string.title_activity_settings);
				icon.setImageResource(R.drawable.ic_dialoge_change);
				builder.setCustomTitle(customTitleView);

				View dialogeView = inflater.inflate(
						R.layout.dialoge_change_capacity, null);
				final EditText edittext = (EditText) dialogeView
						.findViewById(R.id.editText1);
				final TextView error_txt = (TextView) dialogeView
						.findViewById(R.id.error_txt);
				Container container = Container.getContainer(getActivity(), id
						+ "");

				if (sharedpreferences.getInt(Utills.CAPWEIGHTID, Utills.MLID) != Utills.MLID) {
					try {
						edittext.setText(Utills.mltoOz(Utills.numFormat.parse(
								container.getCapacity().toString())
								.doubleValue())
								+ "");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					edittext.setText(container.getCapacity().toString());

				}
				Button set = (Button) dialogeView.findViewById(R.id.button1);
				set.setTag(container);
				set.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Container container = (Container) v.getTag();
						if (container != null) {
							try {
								if (edittext.getText().length() > 0) {
									error_txt.setVisibility(View.GONE);

									if (sharedpreferences.getInt(
											Utills.CAPWEIGHTID, Utills.MLID) != Utills.MLID) {
										container.setCapacity(Utills
												.oztoMl(Utills.numFormat.parse(
														edittext.getText()
																.toString())
														.doubleValue()));
									} else {
										container.setCapacity(Utills.numFormat
												.parse(edittext.getText()
														.toString())
												.doubleValue());
									}
									long update = container.update(
											getActivity(), "_id=?",
											new String[] { container.get_id()
													+ "" });
									if (update > 0) {

										adapter.notifyDataSetChanged();
									}

									changeVal.cancel();

								} else {
									edittext.requestFocus();
									edittext.setError(null);
									error_txt.setVisibility(View.VISIBLE);

								}
							} catch (Exception ex) {

							}
						}
					}
				});
				builder.setView(dialogeView);
				changeVal = builder.create();
				changeVal.show();
			}
		});

		add_new_Container.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(AnimationUtils.loadAnimation(getActivity(),
						R.anim.blink));
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View customTitleView = inflater.inflate(R.layout.dialoge_title,
						null);
				TextView text = (TextView) customTitleView
						.findViewById(R.id.title_dialoge);
				ImageView icon = (ImageView) customTitleView
						.findViewById(R.id.icon_dialoge);

				text.setText(R.string.title_activity_settings);
				icon.setImageResource(R.drawable.ic_dialoge_change);
				builder.setCustomTitle(customTitleView);

				View dialogeView = inflater.inflate(
						R.layout.dialoge_change_capacity, null);
				final EditText edittext = (EditText) dialogeView
						.findViewById(R.id.editText1);
				final TextView error_txt = (TextView) dialogeView
						.findViewById(R.id.error_txt);
				View icon_selectoer_view = dialogeView
						.findViewById(R.id.line_icon);
				icon_selectoer_view.setVisibility(View.VISIBLE);

				final LinearLayout line_icon = (LinearLayout) dialogeView
						.findViewById(R.id.line_icon_bar);
				line_icon.setTag(R.drawable.drinkware_0);
				for (int i = 0; i < line_icon.getChildCount(); i++) {
					View childAt = line_icon.getChildAt(i);
					childAt.setTag(i);
					childAt.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							v.startAnimation(AnimationUtils.loadAnimation(
									getActivity(), R.anim.blink));
							int tag = (Integer) v.getTag();
							line_icon.setTag(drawarray[tag]);

							for (int i = 0; i < line_icon.getChildCount(); i++) {
								View in = line_icon.getChildAt(i);
								int tag2 = (Integer) in.getTag();

								if (tag == tag2) {
									in.setBackgroundResource(R.drawable.edit_text_selectoer);
								} else {
									in.setBackgroundResource(R.drawable.button_selectoer);

								}
							}

						}
					});

				}

				Button set = (Button) dialogeView.findViewById(R.id.button1);
				set.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Container container = new Container(0, 0d, 0, 0);
						if (container != null) {
							try {
								if (edittext.getText().length() > 0) {
									error_txt.setVisibility(View.GONE);

									if (sharedpreferences.getInt(
											Utills.CAPWEIGHTID, Utills.MLID) != Utills.MLID) {
										container.setCapacity(Utills
												.oztoMl(Utills.numFormat.parse(
														edittext.getText()
																.toString())
														.doubleValue()));
									} else {
										container.setCapacity(Utills.numFormat
												.parse(edittext.getText()
														.toString())
												.doubleValue());
									}
									int tag = (Integer) line_icon.getTag();
									container.setDrawable(tag);

									long insert = container
											.insert(getActivity());
									if (insert > 0) {

										adapter.notifyDataSetChanged();
									}

									changeVal.cancel();

								} else {
									edittext.requestFocus();
									edittext.setError(null);
									error_txt.setVisibility(View.VISIBLE);

								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				});
				builder.setView(dialogeView);
				changeVal = builder.create();
				changeVal.show();

			}
		});

		grid.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View customTitleView = inflater.inflate(R.layout.dialoge_title,
						null);
				TextView text = (TextView) customTitleView
						.findViewById(R.id.title_dialoge);
				final ImageView icon = (ImageView) customTitleView
						.findViewById(R.id.icon_dialoge);

				text.setText(R.string.delete);
				icon.setImageResource(android.R.drawable.ic_delete);
				builder.setCustomTitle(customTitleView);
				builder.setMessage("Click ok to delete");

				Container container = Container.getContainer(getActivity(), id
						+ "");
				icon.setTag(container);
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								final Container container = (Container) icon
										.getTag();
								long delete = container.delete(
										getActivity(),
										"_id=?",
										new String[] { container.get_id() + "" });
								if (delete > 0) {

									adapter.notifyDataSetChanged();
								}
								UndoBar bar = new UndoBar(getActivity());
								bar.setMessage(R.string.undo);
								bar.setListener(new Listener() {

									@Override
									public void onUndo(Parcelable token) {
										long insert = container
												.insert(getActivity());
										if (insert > 0) {
											adapter.notifyDataSetChanged();
										}
									}

									@Override
									public void onHide() {

									}
								});

								bar.show(true);
							}
						});
				builder.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				changeVal = builder.create();
				changeVal.show();
				return true;
			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStack();

			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Hello We not Finished yet",
						Toast.LENGTH_LONG).show();
			}
		});
		return inflate;
	}

	private class ContainerAdapter extends BaseAdapter {

		ArrayList<Container> list = new ArrayList<Container>();

		public ContainerAdapter() {
			list = Container.getContainers(getActivity(), null, null);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return list.get(position).get_id();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				LayoutInflater inflater = getActivity().getLayoutInflater();
				convertView = inflater.inflate(R.layout.container_layout, null);
				holder = new Holder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.textView = (TextView) convertView
						.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			Container container = list.get(position);
			holder.icon.setImageResource(container.getDrawable());
			if (sharedpreferences.getInt(Utills.CAPWEIGHTID, Utills.MLID) != Utills.MLID) {

				holder.textView.setText(Utills.numFormat.format(Utills
						.mltoOz(container.getCapacity()))
						+ " "
						+ getResources().getString(R.string.oz));
			} else {
				holder.textView.setText(Utills.numFormat.format(container
						.getCapacity())
						+ " "
						+ getResources().getString(R.string.ml));
			}
			return convertView;
		}

		@Override
		public void notifyDataSetChanged() {
			list.clear();
			list = Container.getContainers(getActivity(), null, null);
			super.notifyDataSetChanged();
		}
	}

	public class Holder {
		ImageView icon;
		TextView textView;

	}
}

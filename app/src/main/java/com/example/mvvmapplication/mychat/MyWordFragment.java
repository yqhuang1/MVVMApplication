package com.example.mvvmapplication.mychat;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmapplication.R;
import com.example.mvvmapplication.adapter.MultiTypeAdapter;
import com.example.mvvmapplication.databinding.FragmentMyWordBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyWordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyWordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private View view;

    private MyWordViewModel myWordViewModel;
    private FragmentMyWordBinding myWordBinding;

    private MultiTypeAdapter<MessageViewModel> mMessageAdapter;

    public MyWordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyWordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyWordFragment newInstance(String param1, String param2) {
        MyWordFragment fragment = new MyWordFragment();
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

        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_my_word, null);

//      这种方式加载是错误的，只能用于Activity加载绑定布局:  myWordBinding = DataBindingUtil.setContentView((MyChatActivity) context, R.layout.fragment_my_word);
        /**用于  Fragment  加载绑定布局
         * parent  可以设置为  container 或 null
         * attachToParent  必须设置为  false，否则会出现BUG**/
        myWordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_word, container, false);

        myWordViewModel = new MyWordViewModel((MyChatActivity) context);
        myWordBinding.setMyWordViewModel(myWordViewModel);

        myWordBinding.wordListRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mMessageAdapter = new MultiTypeAdapter<>(context);
        myWordBinding.wordListRecyclerView.setAdapter(mMessageAdapter);

        myWordViewModel.loadWordList();

        /**返回  Fragment  的  view  **/
        return myWordBinding.getRoot();
    }


}

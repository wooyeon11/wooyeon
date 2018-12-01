package pyneer.full_time_wannabe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.model.FriendData;

/**
 * Created by ddjdd on 2018-11-27.
 */

public class FriendListAdapter extends BaseAdapter {
    // ChatData 객체를 관리하는 ArrayList
    private ArrayList<FriendData> friend_list = new ArrayList<>();

    // 빈 생성자
    public FriendListAdapter() { }

    // ArrayList 에 ExamData 객체를 추가하는 메서드
    public void add(FriendData data) {
        friend_list.add(data);
        notifyDataSetChanged();     // 데이터가 변화됨을 알려준다.
    }

    // 어댑터에서 참조하는 ArrayList 가 가진 데이터의 개수를 반환하는 함수
    @Override
    public int getCount()
    {
        return friend_list.size();
    }

    // 인자로 넘어온 값에 해당하는 데이터를 반환하는 함수
    @Override
    public FriendData getItem(int position)
    {
        return friend_list.get(position);
    }

    // 인자로 넘어온 값에 해당하는 행 ID 를 반환하는 메서드
    @Override
    public long getItemId(int position) { return position; }

    // 인자로 넘어온 값에 해당하는 뷰의 타입을 반환하는 메서드
    @Override
    public int getItemViewType(int position) { return friend_list.get(position).getType(); }

    // getView 메서드로 생성될 수 있는 뷰의 수를 반환하는 메서드
    @Override
    public int getViewTypeCount() { return 1; }

    // 각 항목에 출력될 뷰를 구성하여 반환하는 메서드
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        FriendData data = friend_list.get(position);
        View view = inflater.inflate(R.layout.content_friend_list, parent, false);

        TextView tv_user_name = (TextView)view.findViewById(R.id.tv_user_name);
        tv_user_name.setText(data.getUserName());

        return view;
    }
}

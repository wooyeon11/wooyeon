package pyneer.full_time_wannabe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pyneer.full_time_wannabe.R;
import pyneer.full_time_wannabe.model.ChatData;

/**
 * Created by ddjdd on 2018-11-15.
 */

public class ChatAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_MYCHAT = 0;
    private static final int ITEM_VIEW_TYPE_OTHERCHAT = 1;
    private static final int ITEM_VIEW_TYPE_OTHERCHAT_SERIES = 2;
    private static final int ITEM_VIEW_TYPE_MAX = 3;

    // ChatData 객체를 관리하는 ArrayList
    private ArrayList<ChatData> chat_list = new ArrayList<>();


    public ChatAdapter() { }    // 빈 생성자

    // ArrayList 에 ExamData 객체를 추가하는 메서드
    public void add(ChatData data) {
        chat_list.add(data);
        notifyDataSetChanged();     // 데이터가 변화됨을 알려준다.
    }

    // 어댑터에서 참조하는 ArrayList 가 가진 데이터의 개수를 반환하는 함수
    @Override
    public int getCount()
    {
        return chat_list.size();
    }

    // 인자로 넘어온 값에 해당하는 데이터를 반환하는 함수
    @Override
    public ChatData getItem(int position)
    {
        return chat_list.get(position);
    }

    // 인자로 넘어온 값에 해당하는 행 ID 를 반환하는 메서드
    @Override
    public long getItemId(int position) { return position; }

    // 인자로 넘어온 값에 해당하는 뷰의 타입을 반환하는 메서드
    @Override
    public int getItemViewType(int position) { return chat_list.get(position).type; }

    // getView 메서드로 생성될 수 있는 뷰의 수를 반환하는 메서드
    @Override
    public int getViewTypeCount() { return ITEM_VIEW_TYPE_MAX; }

    // 각 항목에 출력될 뷰를 구성하여 반환하는 메서드
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        View view = null;

        // 표시할 현재 chat 받아오기
        ChatData data = chat_list.get(position);
        // 표시할 현재 chat type 받아오기
        int type = getItemViewType(position);

        // convertView 뷰는 어댑터가 현재 가지고 있는 해당 항목의 뷰객체이다.
        // null 이 넘어오는 경우에만 새로 생성하고, 그렇지않은 경우에는 그대로 사용한다.
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 타입에 따라 각기 다른 XML 리소스로 뷰를 생성한다.
            switch(type) {
                case ITEM_VIEW_TYPE_MYCHAT :
                    view = inflater.inflate(R.layout.content_my_chat, parent, false);
                    TextView tv_msg = (TextView)view.findViewById(R.id.tv_msg);
                    tv_msg.setText(data.getChat());
                    break;
                case ITEM_VIEW_TYPE_OTHERCHAT :
                    view = inflater.inflate(R.layout.content_other_chat, parent, false);
                    tv_msg = (TextView)view.findViewById(R.id.tv_msg);
                    tv_msg.setText(data.getChat());
                    TextView tv_user = (TextView)view.findViewById(R.id.tv_user_name);
                    tv_user.setText(data.getUserName());
                    break;
                case ITEM_VIEW_TYPE_OTHERCHAT_SERIES :
                    view = inflater.inflate(R.layout.content_other_chat_series, parent, false);
                    tv_msg = (TextView)view.findViewById(R.id.tv_msg);
                    tv_msg.setText(data.getChat());
                    break;
                default:
                    view = inflater.inflate(R.layout.content_my_chat, parent, false);
                    tv_msg = (TextView)view.findViewById(R.id.tv_msg);
                    tv_msg.setText(data.getChat());
                    break;
            }
        }
        else {

        }
        return view;
    }

}

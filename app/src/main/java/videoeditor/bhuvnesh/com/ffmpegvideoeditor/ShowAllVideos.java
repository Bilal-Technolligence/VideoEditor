package videoeditor.bhuvnesh.com.ffmpegvideoeditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;

public class ShowAllVideos extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> videoUri = new ArrayList<>();
    ArrayList<String> videoThum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_all_videos );
        recyclerView=(RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchVideos();

    }

    private void fetchVideos() {
    Uri uri;
    int column_index_data,thum;
    Cursor cursor;
    String absolutePathImage=null;
    uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    String[] projection={MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME, MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};
    String orderBy=MediaStore.Images.Media.DATE_TAKEN;

    //testing
        String[] selectedFolder=new String[]{"%Movies%"};
    cursor=getApplicationContext().getContentResolver().query(uri,projection,null,selectedFolder,orderBy+" DESC");
        //testing

    column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
    thum=cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
    while (cursor.moveToNext()){
        absolutePathImage=cursor.getString(column_index_data);
        videoUri.add(absolutePathImage);
        videoThum.add(cursor.getString(thum));

    }
    recyclerView.setAdapter(new AllVideosAdapter(videoUri,videoThum));
    }
}

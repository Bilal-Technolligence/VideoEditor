package videoeditor.bhuvnesh.com.ffmpegvideoeditor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllVideosAdapter extends RecyclerView.Adapter<AllVideosAdapter.ViewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> videoUri;
    ArrayList<String> videoThum;
    Bitmap bmThumbnail;
    public AllVideosAdapter(ArrayList<String> videoUri, ArrayList<String> videoThum) {
                this.videoUri=videoUri;
                this.videoThum=videoThum;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.allvideos_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(videoUri.get(position));
        //holder.imageView.setImageBitmap(videoThum.get(position));

        //testing
        bmThumbnail = ThumbnailUtils.createVideoThumbnail(videoUri.get(position), MediaStore.Video.Thumbnails.MICRO_KIND);
        holder.imageView.setImageBitmap(bmThumbnail);
        //testing
    }

    @Override
    public int getItemCount() {
        return videoUri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.image);
            textView=(TextView) itemView.findViewById(R.id.tittle);
        }
    }
}

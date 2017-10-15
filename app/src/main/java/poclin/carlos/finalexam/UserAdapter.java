package poclin.carlos.finalexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import poclin.carlos.finalexam.model.Usuario;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Usuario> usuarios;

    private OnUserItemClickListener onUserItemClick;

    public UserAdapter(OnUserItemClickListener onUserItemClick){
        this.onUserItemClick = onUserItemClick;
    }

    public void addList(List<Usuario> usuarios){
        this.usuarios = usuarios;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        final Usuario usuario = usuarios.get(position);

        holder.tvId.setText(usuario.getId().toString());
        holder.tvFirstName.setText(usuario.getFirstName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onUserItemClick!=null){
                    onUserItemClick.onItemClick(usuario);
                }
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onUserItemClick != null){
                    onUserItemClick.onEditUsuarioClick(usuario);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(usuarios==null){
            return 0;
        }else{
            return usuarios.size();
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvFirstName;
        Button btnEdit;

        public UserViewHolder (View itemView){
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvFirstName = itemView.findViewById(R.id.tv_firstName);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }

    }
    public interface OnUserItemClickListener{
        void onItemClick(Usuario usuario);
        void onEditUsuarioClick(Usuario usuario);
    }
}

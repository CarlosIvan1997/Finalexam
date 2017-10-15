package poclin.carlos.finalexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import poclin.carlos.finalexam.model.DaoSession;
import poclin.carlos.finalexam.model.Usuario;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserItemClickListener{

    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.rv_usuarios)
    RecyclerView rvUsuarios;
    @BindView(R.id.btn_add)
    Button btnAdd;

    private List<Usuario> usuarios;
    private UserAdapter userAdapter;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FinalApplication finalApplication = (FinalApplication) getApplication();
        daoSession = finalApplication.getDaoSession();

        userAdapter = new UserAdapter(this);

        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        rvUsuarios.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvUsuarios.setAdapter(userAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usuarios = daoSession.getUsuarioDao().loadAll();
        userAdapter.addList(usuarios);
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        FormUserActivity.start(this,null);
    }

    @Override
    public void onItemClick(Usuario usuario) {
        daoSession.delete(usuario);
        usuarios = daoSession.getUsuarioDao().loadAll();
        userAdapter.addList(usuarios);
        Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditUsuarioClick(Usuario usuario) {
        FormUserActivity.start(this,usuario);
    }
}

package poclin.carlos.finalexam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import poclin.carlos.finalexam.model.DaoSession;
import poclin.carlos.finalexam.model.Usuario;

public class FormUserActivity extends AppCompatActivity {

    @BindView(R.id.et_firstName)
    EditText etFirstName;
    @BindView(R.id.et_lastName)
    EditText etLastName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_save)
    Button btnSave;

    private Usuario usuario;
    private boolean actualizar;
    private DaoSession daoSession;
    
    public static void start(Context context, Usuario usuario) {
        Intent starter = new Intent(context, FormUserActivity.class);
        starter.putExtra("usuario", usuario);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
        ButterKnife.bind(this);

        usuario = getIntent().getParcelableExtra("usuario");

        FinalApplication finalApplication = (FinalApplication) getApplication();
        daoSession = finalApplication.getDaoSession();

        if(usuario==null){
            btnSave.setText("AGREGAR");
            actualizar = false;
        }else{
            etFirstName.setText(usuario.getFirstName());
            etLastName.setText(usuario.getLastName());
            etAge.setText(usuario.getAge().toString());
            etEmail.setText(usuario.getEmail());
            etPassword.setText(usuario.getPassword());
            btnSave.setText("ACTUALIZAR");
            actualizar = true;
        }
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        if(actualizar){
            actualizarUsuario();
        }else{
            agregarUsuario();
        }
    }

    private void agregarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setFirstName(etFirstName.getText().toString());
        usuario.setLastName(etLastName.getText().toString());
        usuario.setAge(Integer.parseInt(etAge.getText().toString()));
        usuario.setEmail(etEmail.getText().toString());
        usuario.setPassword(etPassword.getText().toString());

        daoSession.getUsuarioDao().insert(usuario);
        Toast.makeText(this, "El Usuario se ha agregado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void actualizarUsuario(){
        usuario.setFirstName(etFirstName.getText().toString());
        usuario.setLastName(etLastName.getText().toString());
        usuario.setAge(Integer.parseInt(etAge.getText().toString()));
        usuario.setEmail(etEmail.getText().toString());
        usuario.setPassword(etPassword.getText().toString());

        daoSession.getUsuarioDao().update(usuario);
        Toast.makeText(this, "El usuario se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}

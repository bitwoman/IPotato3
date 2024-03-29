package com.example.ipotato3.classes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ipotato3.R;
import com.example.ipotato3.daos.ProdutoDAO;
import com.example.ipotato3.daos.UsuarioDAO;

public class Login extends Fragment implements View.OnClickListener{

    //Atributos
    NavController navController;
    Button buttonLogin, buttonRegistrar;
    TextView buttonPopularBanco;
    EditText editTextNomeUsuario, editTextSenhaUsuario;
    UsuarioDAO tabelaUsuario;
    ProdutoDAO tabelaProduto;

    //construtor
    public Login() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ((DrawerLocker) requireActivity()).liberarMenu(false);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabelaUsuario = new UsuarioDAO(getContext());

        navController = Navigation.findNavController(view);

        editTextNomeUsuario = view.findViewById(R.id.idEditTextNomeUsuario);
        editTextSenhaUsuario = view.findViewById(R.id.idEditTextSenhaUsuario);

        buttonLogin = view.findViewById(R.id.idButtonLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegistrar = view.findViewById(R.id.idButtonRegistrar);
        buttonRegistrar.setOnClickListener(this);

        buttonPopularBanco = view.findViewById(R.id.idButtonPopularBanco);
        buttonPopularBanco.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.idButtonLogin:
                if(editTextNomeUsuario.getText().toString().isEmpty() || editTextSenhaUsuario.getText().toString().isEmpty()){
                    editTextNomeUsuario.setHint("Campo obrigatório!");
                    editTextNomeUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

                    editTextSenhaUsuario.setHint("Campo obrigatório!");
                    editTextSenhaUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));
                } else{
//                    navController.navigate(R.id.action_login_to_iniciarPedido);
                    getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, IniciarPedido.class, null).addToBackStack(null).commit();
//                    //Validação se as credenciais de login existem no banco de dados, se sim, o usuário será logado.
//                    String nomeUsuario = editTextNomeUsuario.getText().toString().trim();
//                    String senhaUsuario = editTextSenhaUsuario.getText().toString().trim();
//
//                    Boolean validarLogin = tabelaUsuario.validarLogin(nomeUsuario,senhaUsuario);
//
//                    if(validarLogin == true){
//                        navController.navigate(R.id.action_login_to_cardapio);
//                    }else{
//                        Toast.makeText(getContext(), "LOGIN INVÁLIDO", Toast.LENGTH_SHORT).show();
//                    }
                }
                break;

            case R.id.idButtonRegistrar:
//                navController.navigate(R.id.action_login_to_cadastroDeUsuario); //alterar
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, CadastroDeUsuario.class, null).addToBackStack(null).commit();
            case R.id.idButtonPopularBanco:
//                tabelaProduto.popularBD();
                break;
        }
    }
}
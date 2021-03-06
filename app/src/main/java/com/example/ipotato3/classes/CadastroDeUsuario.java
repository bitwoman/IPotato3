package com.example.ipotato3.classes;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ipotato3.R;
import com.example.ipotato3.daos.UsuarioDAO;
import com.example.ipotato3.models.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroDeUsuario extends Fragment implements View.OnClickListener{

    //Atributos
    NavController navController;
    EditText editTextNomeCompleto, editTextEmail, editTextNomeDeUsuario, editTextSenha, editTextConfirmarSenha;
    Button buttonRegistrar;
    UsuarioDAO tabelaUsuario;
    Usuario usuarioParaCadastrar;

    //Construtor
    public CadastroDeUsuario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ((DrawerLocker) requireActivity()).liberarMenu(true);
        return inflater.inflate(R.layout.fragment_cadastro_de_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabelaUsuario = new UsuarioDAO(getContext());

        navController = Navigation.findNavController(view);

        editTextNomeCompleto = view.findViewById(R.id.idEditTextNomeCadastro);
        editTextEmail = view.findViewById(R.id.idEditTextEmailCadastro);
        editTextNomeDeUsuario = view.findViewById(R.id.idEditTextUsuarioCadastro);
        editTextSenha = view.findViewById(R.id.idEditTextSenhaCadastro);
        editTextConfirmarSenha = view.findViewById(R.id.idEditTextConfirmarSenhaCadastro);

        buttonRegistrar = view.findViewById(R.id.idButtonRegistrarCadastro);
        buttonRegistrar.setOnClickListener(this);
    }

    //M??todo do evento de click de bot??es padr??es na tela (cadastrar usuario)
    @Override
    public void onClick(View view) {
        if(this.validarCampos() == true) {
            tabelaUsuario.inserir(usuarioParaCadastrar);
            navController.navigate(R.id.action_cadastroDeUsuario_to_login);
        }else{
            Toast.makeText(getContext(), "CADASTRO N??O REALIZADO!", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean validarCampos() {
        String nome = editTextNomeCompleto.getText().toString().trim();
        String emailUsuario = editTextEmail.getText().toString().trim();
        String nomeUsuario = editTextNomeDeUsuario.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        String senhaConfirmada = editTextConfirmarSenha.getText().toString().trim();


        if (nome.isEmpty() || emailUsuario.isEmpty() || nomeUsuario.isEmpty() || senha.isEmpty() || senhaConfirmada.isEmpty()) {
            editTextNomeCompleto.setHint("Campo obrigat??rio!");
            editTextNomeCompleto.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextEmail.setHint("Campo obrigat??rio!");
            editTextEmail.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextNomeDeUsuario.setHint("Campo obrigat??rio!");
            editTextNomeDeUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextSenha.setHint("Campo obrigat??rio!");
            editTextSenha.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextConfirmarSenha.setHint("Campo obrigat??rio!");
            editTextConfirmarSenha.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));
            return false;
        }
        // Se os campos N??O ESTIVEREM vazios!
        else {
            Pattern patternEmail = Patterns.EMAIL_ADDRESS;
            Pattern validacaoNome = Pattern.compile("[A-Za-z??????????????????????????????????????????????????????????????'\\s]+");

            Pattern validacaoSenha = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //tem que conter n??mero
                    "(?=.*[a-z])" +         //tem que letra min??scula
                    "(?=.*[A-Z])" +         //tem que conter letra mai??scula
                    "(?=.*[@#$%^!&+=])" +    //tem que ter caracter especial
                    "(?=\\S+$)" +           //n??o pode ter espa??o
                    ".{8,}" +               //tem que ter pelo menos 8 caracteres
                    "$");

            Pattern validacaoSenhaConfirmada = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //tem que conter n??mero
                    "(?=.*[a-z])" +         //tem que letra min??scula
                    "(?=.*[A-Z])" +         //tem que conter letra mai??scula
                    "(?=.*[@#$%^!&+=])" +    //tem que ter caracter especial
                    "(?=\\S+$)" +           //n??o pode ter espa??o
                    ".{8,}" +               //tem que ter pelo menos 8 caracteres
                    "$");

            //Instancia da classe Matcher para validar se o valor da vari??vel ?? compat??vel com o padr??o decretado no regex
            Matcher matchSenha = validacaoSenha.matcher(senha);
            Matcher matchNome = validacaoNome.matcher(nome);
            Matcher matchSenhaConfirmada = validacaoSenhaConfirmada.matcher(senhaConfirmada);

            //Resultado em booleano da valida????o do regex nos campos
            boolean respostaValicaoNome = matchNome.matches();
            boolean validacaoEmail = patternEmail.matcher(editTextEmail.getText().toString().trim()).matches();
            boolean respostaValicaoSenha = matchSenha.matches();
            boolean respostaValicaoSenhaConfirmada = matchSenhaConfirmada.matches();
            boolean validaNomeUsuarioExistente = tabelaUsuario.validarUsuario(nomeUsuario);

            //Inst??ncia da classe Usuario para a inser????o dos dados do usu??rio para, posterior, ser inserida no banco de dados - na tabela de usuario.
            usuarioParaCadastrar = new Usuario(0, nome, emailUsuario, nomeUsuario, senha);

            //Verifica se as valida????es est??o corretas e retornam verdadeiro para que o processo de cadastro prossiga
            if (respostaValicaoNome == true) {
                if (validacaoEmail == true) {
                    if (validaNomeUsuarioExistente == false) {
                        if (respostaValicaoSenha == true) {
                            if (respostaValicaoSenhaConfirmada == true) {
                                if (senha.equals(senhaConfirmada)) {
                                    usuarioParaCadastrar = new Usuario(0, nome, emailUsuario, nomeUsuario, senha);
                                    return true;
                                } else {
                                    Toast.makeText(getContext(), "As senhas s??o diferentes!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Senha fora de padr??o!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Senha fora de padr??o!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Nome de usu??rio j?? existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "E-MAIL INCORRETO!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Nome fora do padr??o!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}

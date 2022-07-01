package com.example.ipotato3.classes;

import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ipotato3.R;
import com.example.ipotato3.adapters.ProdutoAdapter;
import com.example.ipotato3.daos.ProdutoDAO;
import com.example.ipotato3.interfaces.BackKeyPressedListener;
import com.example.ipotato3.models.Produto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Cardapio extends Fragment {


    //Construtor
    public Cardapio() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Observe que o primeiro argumento do inflate é o layout com a ListView que criamos no primeiro passo
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        ProdutoDAO tabelaProduto = new ProdutoDAO(getContext());

        // Aqui você instancia sua ListView
        ListView listaProdutos = (ListView) view.findViewById(R.id.idListaDeProdutosCardapio);
//        List<Produto> produtos = null; // Obtenha sua lista de objetos aqui

//        ProdutoAdapter adaptador = new ProdutoAdapter(getActivity(), android.R.layout.simple_list_item_1, Produto.getProdutos());
        ProdutoAdapter adaptador = new ProdutoAdapter(getActivity(), android.R.layout.simple_list_item_1, tabelaProduto.getAllProducts());
        listaProdutos.setAdapter(adaptador);

        return view;
    }

    ////    Método responsável pela ação de fechar o menu caso o usuário aperte o botão de "voltar" no Android - ao invés de fechar o App ou voltar para alguma outra aba.
//    public void onBackPressed() {
////        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
////            drawerLayout.closeDrawer(GravityCompat.START);
////        } else {
////            onBackPressed();
////        }
//    }

}
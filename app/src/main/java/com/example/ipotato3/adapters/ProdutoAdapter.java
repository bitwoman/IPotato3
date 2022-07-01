package com.example.ipotato3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ipotato3.R;
import com.example.ipotato3.models.Produto;
import java.util.List;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    //Atributos
    private Context context;
    private List<Produto> dataset;
//    ProdutoDAO tabelaProduto;
//    Produto produto;

    //Construtor
    public ProdutoAdapter(Context context, int simple_list_item_1, List<Produto> dataset) {
        super(context, 0, dataset);
        this.dataset = dataset;
        this.context = context;
    }

    //Retorna o tamanho de lista
    @Override
    public int getCount() {
        return dataset.size();
    }

    //Pegar o item (produto) da lista daquela linha. objeto na posicao i
    @Override
    public Produto getItem(int i) {
        return dataset.get(i);
    }

    //Pegar o id desse item (produto) da lista
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View linhaProduto, ViewGroup viewGroup) {
        //Pra setar os atributos do produto específico, de acordo com o seu id
        Produto produto = dataset.get(position);

        if(linhaProduto == null)
            linhaProduto = LayoutInflater.from(context).inflate(R.layout.fragment_produto_cardapio, null);

        //inflar o layout xml, acessar os campos e colocar os valores,
        //Acesar imgView, e txt's

        //Inflando os tópicos do produto
        ImageView imageViewProdutoPromo = linhaProduto.findViewById(R.id.idImageViewItemBatata);
        TextView textViewNomeProdutoPromo = linhaProduto.findViewById(R.id.idTxtNomeProduto);
        TextView textViewPrecoProdutoPromo = linhaProduto.findViewById(R.id.idTxtPreco);

        Button buttonRemoverQtdeItem = linhaProduto.findViewById(R.id.idButtonRemoverQtdeItem);
        Button buttonAdicionarQtdeItem = linhaProduto.findViewById(R.id.idButtonAdicionarQtdeItem);
//        Button buttonEditarProduto = view.findViewById(R.id.idButtonEditarProduto);
//        Button buttonExcluirProduto = view.findViewById(R.id.idButtonExcluirProduto);

        buttonRemoverQtdeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "FUNCIONA FILHA DA PUTA", Toast.LENGTH_SHORT).show();
            }
        });

        //Setar os atributos
        textViewNomeProdutoPromo.setText(produto.getNome());
        textViewPrecoProdutoPromo.setText((String.valueOf(produto.getPreco())));
//        imageViewProdutoPromo.setImageBitmap(produto.getImagem());

        //O adapter da lista -> Pega os dados de uma forma para serem exibidos de outro.
        return linhaProduto;
    }

    //Método de evento de click
    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idButtonEditarProduto:
                Intent it = new Intent(context, GerenciarCardapio.class);
                it.putExtra("produto", String.valueOf(produto.getId()));
                context.startActivity(it);
                break;
            case R.id.idButtonExcluirProduto:
                //Pra setar os atributos do produto específico, de acordo com o seu id
//                    produto = dataset.get(i);

                //Instanciando o objeto da caixa de alerta
                AlertDialog.Builder alertDelete = new AlertDialog.Builder(context);

                //Criando título da janela e a mensagem que será exibida para o usuário
                alertDelete.setTitle(R.string.title_alert_dialog);
                alertDelete.setMessage(R.string.alert_dialog_warning);

                //Inflando a caixa de alerta junto aos botões respectivos de confirmar e cancelar

                //Confirmar
                alertDelete.setPositiveButton(R.string.alert_dialog_confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tabelaProduto.deletar(String.valueOf(produto.getId()));

                        dataset.remove(produto);
                        notifyDataSetChanged();
                    }
                });

                //Cancelar
                alertDelete.setNegativeButton(R.string.alert_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDelete.show();
                break;
            case R.id.idButtonAdicionarQtdeItem:
                Toast.makeText(context, "FUNCIONA FILHA DA PUTA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idButtonRemoverQtdeItem:
                Toast.makeText(context, "FUNCIONA FILHA DA PUTA", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/
}
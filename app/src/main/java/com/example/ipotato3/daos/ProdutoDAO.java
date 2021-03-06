package com.example.ipotato3.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.ipotato3.models.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends SQLiteOpenHelper {

    //Nome do banco e versão
    public final static String NOME_BANCO = "ipotato";
    public final static int VERSAO_BANCO = 5;

    //Construtor
    public ProdutoDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        validarTabela();
    }

    public void validarTabela(){
        //Criando variável do tipo SQLiteDatabase e chamando o método getWritableDatabase(), funciona para alterações e não somente leitura
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS produto(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, "+
                "preco REAL, " +
                "desconto REAL, " +
                "quantidade INTEGER);");
    }

    //On create para executar o SQL que vai criar as tabelas do banco e as colunas.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //, imagem BLOB
        sqLiteDatabase.execSQL("CREATE TABLE produto(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, "+
                "preco REAL, " +
                "desconto REAL, " +
                "quantidade INTEGER);");
    }

    //função de atualização do banco caso já exista uma tabela com o nome duplicado
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produto");
        onCreate(sqLiteDatabase);
    }

    //Função de inserção de registros no banco
    public void inserir(Produto p){

        //criando variável do tipo SQLiteDatabase e chamando o método getWritableDatabase(), funciona para alterações e não somente leitura
        SQLiteDatabase db = this.getWritableDatabase();

        // criando uma instância de ContentValues que é responsável por fazer o get dos campos, no caso inserir os valores nas
        // respectivas colunas da tabela no banco
        ContentValues insere_valor = new ContentValues();
        insere_valor.put("nome", p.getNome());
//        insere_valor.put("imagem", p.getImagem());
        insere_valor.put("descricao", p.getDescricao());
        insere_valor.put("preco", p.getPreco());
        insere_valor.put("desconto", p.getDesconto());
        insere_valor.put("quantidade", p.getQuantidadeProduto());

        //Chamando minha variavel db e passando a função de inserção no banco, colocando o nome da tabela e passando os valores
        // coletados através do insere_valores
        db.insert("Produto", null, insere_valor);

//        db.close();
    }

    //Função para atualizar registros no banco
    public void atualizar(String id, Produto p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
//        content.put("_id", id);
        //nome das minhas colunas / get da minha classe Produto
        content.put("nome", p.getNome());
        //        insere_valor.put("imagem", p.getImagem());
        content.put("descricao", p.getDescricao());
        content.put("preco", p.getPreco());
        content.put("desconto", p.getDesconto());
        content.put("quantidade", p.getQuantidadeProduto());

        //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que
        //esta atualizando
        db.update("produto", content,"_id=?", new String[]{id});

        //encerrando a conexão com o banco
        db.close();
    }

    //Função de deleção de registros no banco
    public void deletar(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        //Chamando a função de delete, passando a tabela que eu vou mexer e o id do usuário que eu quero deletar
        db.delete("produto", "_id=?", new String[]{id});

        db.close();
    }

    //Função de listagem de registros no banco
    public List<Produto> getAllProducts(){
        SQLiteDatabase database = getReadableDatabase();

        List<Produto> listaProdutos= new ArrayList<Produto>();

        String[] colunasDatabase = new String[]{"_id","nome", "descricao","preco", "desconto", "quantidade"};
        Cursor cursor = database.query("produto", colunasDatabase,null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
//                byte[] converterValor = cursor.getBlob(cursor.getColumnIndexOrThrow("iconeContato"));
//                Bitmap imagemBitmap = BitmapFactory.decodeByteArray(converterValor, 0, converterValor.length);

                Produto produto = new Produto(
                        cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                        cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("preco")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("desconto")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantidade"))
                );
                listaProdutos.add(produto);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return listaProdutos;
    }

    public void popularBD(){
        Produto pr = new Produto(
                0,
                "Ana",
                "Vendendo a Anaju",
                100,
                10,
                1
        );

        this.inserir(pr);
    }
}



public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }
    public void inserir(long valor){
        No novoNo = new No();
        novoNo.item = valor;
        novoNo.esq = null;
        novoNo.dir = null;

        if (raiz == null) {
            raiz = novoNo;
        }
        else{
            No atual = raiz;
            No anterior;
            while(true){
                anterior = atual;
                if(valor <= atual.item){
                    atual = atual.esq;
                    if(atual == null){
                        anterior.esq = novoNo;
                        return;
                    }
                }
                else{
                    atual = atual.dir;
                    if(atual == null){
                        anterior.dir = novoNo;
                        return;
                    }
                }
            }
        }
    };

    public No procurar(long valor){
        if (raiz == null) {
            return null;
        }
        No atual = raiz;
        while(atual.item != valor){
            if(valor <= atual.item){
                atual = atual.esq;
            }else{
                atual = atual.dir;
            }
            if(atual == null){
                return null;
            }
        }
        return atual;
    }

    public boolean remover(long valor){
        if (raiz == null) {
            return false;
        }
        No atual = raiz;
        No pai = raiz;
        boolean filho_esq = true;

        while(atual.item != valor){
            pai = atual;
            if(valor <= atual.item){
                atual = atual.esq;
                filho_esq = true;
            }else{
                atual = atual.dir;
                filho_esq=false;
            }
            if(atual == null){
                return false;
            }
        }
        if(atual.esq == null && atual.dir == null){
            if(atual == raiz){
                raiz = null;
            } else if (filho_esq) {
                pai.esq = null;
            }else{
                pai.dir = null;
            }
        }
        else if (atual.dir == null) {
            if(atual == raiz){
                raiz = atual.esq;
            } else if (filho_esq) {
                pai.esq = atual.esq;
            }else{
                pai.dir = atual.esq;
            }
        }
        else if (atual.esq == null) {
            if (atual == raiz) {
                raiz = atual.dir; // se raiz
            }
            else if (filho_esq) {
                pai.esq = atual.dir; // se for filho a esquerda do pai
            }
            else{
                pai.dir = atual.dir; // se for  filho a direita do pai
            }
        }
        else{
            No sucessor = no_sucessor(atual);
            if(atual == raiz) {
                raiz = sucessor;
            } else if (filho_esq) {
                pai.esq = sucessor;
            }else{
                pai.dir = sucessor;
            }
            sucessor.esq = atual.esq;
        }
        return true;
    }

    public No no_sucessor(No apaga){
        No pai_do_sucessor = apaga;
        No sucessor = apaga;
        No atual = apaga.dir;

        while(atual.dir != null){
            pai_do_sucessor = sucessor;
            sucessor = atual;
            atual = atual.esq;
        }
        if(sucessor != apaga.dir){
            pai_do_sucessor.esq = sucessor.dir;
            sucessor.dir = apaga.dir;
        }
        return sucessor;
    }

    public void caminhar(){
        System.out.println("\n Exibindo In Order");
        inOrder(raiz);
        System.out.println("\n Exibindo Pre Order");
        preOrder(raiz);
        System.out.println("\n Exibindo Pos Order");
        posOrder(raiz);
        System.out.print("\n Altura da arvore: " + altura(raiz));
        System.out.print("\n Quantidade de folhas: " + folhas(raiz));
        System.out.print("\n Quantidade de Nós: " + contarNos(raiz));
        if (raiz != null ) {  // se arvore nao esta vazia
            System.out.print("\n Valor minimo: " + min().item);
            System.out.println("\n Valor maximo: " + max().item);
        }
    }

    public void inOrder(No atual) {
        if (atual != null) {
            inOrder(atual.esq);
            System.out.print(atual.item + " ");
            inOrder(atual.dir);
        }
    }

    public void preOrder(No atual) {
        if (atual != null) {
            System.out.print(atual.item + " ");
            preOrder(atual.esq);
            preOrder(atual.dir);
        }
    }

    public void posOrder(No atual) {
        if (atual != null) {
            posOrder(atual.esq);
            posOrder(atual.dir);
            System.out.print(atual.item + " ");
        }
    }

    public int altura(No atual){
        if(atual == null || (atual.esq == null && atual.dir == null)) {
            return 0;
        }else{
            if(altura(atual.esq)> altura(atual.dir)){
                return (1 + altura(atual.esq));
            }else{
                return (1 + altura(atual.dir));
            }
        }
    }

    public int folhas(No atual) {
        if(atual == null) return 0;
        if(atual.esq == null && atual.dir == null) return 1;
        return folhas(atual.esq) + folhas(atual.dir);
    }

    public int contarNos(No atual) {
        if(atual == null)  return 0;
        else return ( 1 + contarNos(atual.esq) + contarNos(atual.dir));
    }

    public No min() {
        No atual = raiz;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.esq;
        }
        return anterior;
    }

    public No max() {
        No atual = raiz;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.dir;
        }
        return anterior;
    }
}

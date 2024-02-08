package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree {
    private Node root;

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null)
            return false;
        if (valor == node.valor)
            return true;
        if (valor < node.valor)
            return buscaElemento(node.left, valor);
        else
            return buscaElemento(node.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");
        Node minNode = minimo(root);
        return minNode.valor;
    }

    private Node minimo(Node node) {
        if (node.left == null)
            return node;
        return minimo(node.left);
    }

    @Override
    public int maximo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");
        Node maxNode = maximo(root);
        return maxNode.valor;
    }

    private Node maximo(Node node) {
        if (node.right == null)
            return node;
        return maximo(node.right);
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null)
            return new Node(valor);
        if (valor < node.valor)
            node.left = insereElemento(node.left, valor);
        else if (valor > node.valor)
            node.right = insereElemento(node.right, valor);
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null)
            return null;
        if (valor < node.valor)
            node.left = remove(node.left, valor);
        else if (valor > node.valor)
            node.right = remove(node.right, valor);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                node.valor = minimo(node.right).valor;
                node.right = remove(node.right, node.valor);
            }
        }
        return node;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> elements = new ArrayList<>();
        preOrdem(root, elements);
        return elements.stream().mapToInt(Integer::intValue).toArray();
    }

    private void preOrdem(Node node, List<Integer> elements) {
        if (node != null) {
            elements.add(node.valor);
            preOrdem(node.left, elements);
            preOrdem(node.right, elements);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> elements = new ArrayList<>();
        emOrdem(root, elements);
        return elements.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdem(Node node, List<Integer> elements) {
        if (node != null) {
            emOrdem(node.left, elements);
            elements.add(node.valor);
            emOrdem(node.right, elements);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> elements = new ArrayList<>();
        posOrdem(root, elements);
        return elements.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdem(Node node, List<Integer> elements) {
        if (node != null) {
            posOrdem(node.left, elements);
            posOrdem(node.right, elements);
            elements.add(node.valor);
        }
    }

    private static class Node {
        int valor;
        Node left;
        Node right;

        Node(int valor) {
            this.valor = valor;
        }
    }
}

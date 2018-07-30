package com.dataStructure.tree.BinaryTree;


/**
 * @ Create by ostreamBaba on 18-7-29
 * @ ����
 */

public class BinarySearchTree {

    public TreeNode root;

    public BinarySearchTree(int treeDate) {
        root=new TreeNode(treeDate);
    }

    public BinarySearchTree() { }

    //���������벿�� ʹ�õݹ�
    public void buildByRecursion(TreeNode currentNode, int treeDate) {
        if(root==null){
            root=new TreeNode(treeDate);
            root.parent=null;
            return;
        }

        if(treeDate<currentNode.threeDate){
            if(currentNode.leftChildNode!=null){
                buildByRecursion(currentNode.leftChildNode,treeDate);
            }else {
                currentNode.leftChildNode=new TreeNode(treeDate);
                currentNode.leftChildNode.parent=currentNode;
            }
        }else{
            if(currentNode.rightChildNode!=null){
                buildByRecursion(currentNode.rightChildNode,treeDate);
            }else{
                currentNode.rightChildNode=new TreeNode(treeDate);
                currentNode.rightChildNode.parent=currentNode;
            }
        }
    }

    public void buildByIteration(TreeNode currentNode,int treeDate){
        if(root==null){
            root=new TreeNode(treeDate);
            root.parent=null;
            return;
        }

        //ע�� �����ж�currentNode==null ��Ϊ�����Ѿ���ָ��ԭ���Ķ�����
        while (true){
            if(treeDate<currentNode.threeDate){
                if(currentNode.leftChildNode!=null) {
                    currentNode = currentNode.leftChildNode;
                }else{
                    currentNode.leftChildNode=new TreeNode(treeDate);
                    currentNode.leftChildNode.parent=currentNode;
                    return;
                }
            }else{
                if(currentNode.rightChildNode!=null) {
                    currentNode = currentNode.rightChildNode;
                }else {
                    currentNode.rightChildNode=new TreeNode(treeDate);
                    currentNode.rightChildNode.parent=currentNode;
                    return;
                }
            }
        }
    }

    //��ѯ����������
    public boolean search(TreeNode currentNode,int date){
        if(currentNode==null){
            throw new IllegalArgumentException("�����ڸýڵ�");
        }
        if(currentNode.threeDate==date){
            return true;
        }
        if(date<currentNode.threeDate){
            return search(currentNode.leftChildNode,date);
        }else{
            return search(currentNode.rightChildNode,date);
        }
    }

    //�����汾
    public TreeNode searchIterative(TreeNode currentNode,int date){
        while (true){
            if(currentNode==null){
                throw new IllegalArgumentException("�����ڸýڵ�");
            }
            if(currentNode.threeDate==date){
                break;
            }
            if(date<currentNode.threeDate){
                currentNode=currentNode.leftChildNode;
            }else{
                currentNode=currentNode.rightChildNode;
            }
        }
        return currentNode;
    }

    //�������ؼ���Ԫ�� --һֱ��right����ֱ��Ϊnull
    public TreeNode findMax(){
        TreeNode currentNode=root;
        while (currentNode.rightChildNode!=null){
            currentNode=currentNode.rightChildNode;
        }
        return currentNode;
    }

    //������С�ؼ���Ԫ�� --һֱ����left����ֱ��null
    public TreeNode findMin(){
        TreeNode currentNode=root;
        while (currentNode.leftChildNode!=null){
            currentNode=currentNode.leftChildNode;
        }
        return currentNode;
    }

    //�ݹ�汾
    public TreeNode findMinIterative(TreeNode node){
        TreeNode currentNode=node;
        if(node.leftChildNode!=null){
            currentNode=findMinIterative(node.leftChildNode);
        }
        return currentNode;
    }

    //�ݹ�汾
    public TreeNode findMaxIterative(TreeNode node){
        TreeNode currentNode=node;
        if(node.rightChildNode!=null){
            currentNode=findMaxIterative(node.rightChildNode);
        }
        return currentNode;
    }

    //���Һ��
    //���1�� ���x�ڵ����������Ϊ�� ��ôx�ĺ��y�ľ���x�ĵ��������е�����ڵ�
    //���2�� ���x�ڵ��������Ϊ�� ����Ѱ�Ҹ��׽ڵ� �����׽ڵ�����������ڵ�ǰ�ڵ� ��ô�ø��׽ڵ����x�ĺ��
    public TreeNode getNext(TreeNode node){

        if(node==null){
            throw new IllegalArgumentException("�ýڵ㲻�Ϸ�");
        }

        if(node.rightChildNode!=null){
            return findMinIterative(node.rightChildNode);
        }

        TreeNode y=node.parent;
        while (y!=null&&y.leftChildNode!=node){
            node=y;
            y=node.parent;
        }

        if(y==null){
            throw new IllegalArgumentException("�ýڵ�û�к�̽ڵ�");
        }

        return y;
    }

    //Ѱ��ǰ�� ���� �෴
    //���1�� ���x����������Ϊ�� ��ôx��ǰ��y����x�������������ҽڵ�
    //���2�� ���x��������Ϊ�� ����Ѱ�Ҹ��׽ڵ� �����׽ڵ�����������ڵ�ǰ�ڵ� ��ô�ø��׽ڵ����x��ǰ��
    public TreeNode getPre(TreeNode node){
        if(node==null){
            throw new IllegalArgumentException("�ýڵ㲻�Ϸ�");
        }
        if(node.leftChildNode!=null){
            return findMaxIterative(node.leftChildNode);
        }

        TreeNode y=node.parent;
        while (y!=null&&y.rightChildNode!=node){
            node=y;
            y=node.parent;
        }

        if(y==null){
            throw new IllegalArgumentException("�ýڵ�û��ǰ���ڵ�");
        }

        return y;
    }

    //�ƶ�����
    //��һ����vΪ�������� �滻 һ����uΪ��������
    public void transplant(BinarySearchTree binarySearchTree,TreeNode u,TreeNode v){
        if(u.parent==null){  //��uΪT�����������
            binarySearchTree.root=v;
        }else if(u==u.parent.leftChildNode){
            u.parent.leftChildNode=v;
        }else if(v==v.parent.rightChildNode){
            u.parent.rightChildNode=v;
        }
        if(v!=null){
            v.parent=u.parent;
        }
    }

    //ɾ���ڵ�
    public void delete(BinarySearchTree binarySearchTree,TreeNode deleteNode){

        if(deleteNode.leftChildNode==null){ //û������(���Һ��ӻ���NIL)
            transplant(binarySearchTree,deleteNode,deleteNode.rightChildNode);
        }else if(deleteNode.rightChildNode==null){ //û���Һ���(�����ӻ���NIL)
            transplant(binarySearchTree,deleteNode,deleteNode.leftChildNode);
        }else{
            TreeNode y=getNext(deleteNode); //��z�ĺ��
            //���������y��z�������� y����z��������

            // y�滻y�������� ��̽ڵ�����ֻ��һ�����ӽڵ�(�Һ��ӻ���NIL)
            if(y.parent!=deleteNode){
                transplant(binarySearchTree,y,y.rightChildNode);
                //����y���z����������˫�׽ڵ�
                y.rightChildNode=deleteNode.rightChildNode;
                y.rightChildNode.parent=y;
            }

            //��y���������滻��z�������� ����z�滻��y
            y.leftChildNode=deleteNode.leftChildNode;
            y.leftChildNode.parent=y;
            transplant(binarySearchTree,deleteNode,y);
        }

/*
        //��ɾ���ڵ�z�����Һ��ӽڵ�Ϊ�� ֱ��ɾ���ڵ� ���޸�z�ĸ��ڵ� ��NIL��Ϊ�������滻z
        if(deleteNode.leftChildNode==null&&deleteNode.rightChildNode==null){
            if(z.leftChildNode==deleteNode){
                z.leftChildNode=null;
            }else{
                z.rightChildNode=null;
            }
        }else {
            if(deleteNode.leftChildNode!=null&&deleteNode.rightChildNode==null){
                if(z.leftChildNode==deleteNode){
                    z.leftChildNode=deleteNode.leftChildNode;
                }else{
                    z.rightChildNode=deleteNode.leftChildNode;
                }
            }else if(deleteNode.leftChildNode==null){
                //��deleteNode.rightChildNode!=null
                if(z.leftChildNode==deleteNode){
                    z.leftChildNode=deleteNode.rightChildNode;
                }else{
                    z.rightChildNode=deleteNode.rightChildNode;
                }
            }else{ //�Һ��
                TreeNode nextNode=getNext(deleteNode);
                if(nextNode==deleteNode.rightChildNode){ //�����̲���ɾ���ڵ��������(��̽ڵ�������һ��ΪNULL)
                    nextNode.leftChildNode=deleteNode.leftChildNode;
                    if(z.leftChildNode==deleteNode){
                        z.leftChildNode=nextNode;
                    }else{
                        z.rightChildNode=nextNode;
                    }
                }else{
                    //��z�ĺ��y ռ������z��λ��
                    nextNode.parent=nextNode.rightChildNode;//�ú�̽ڵ�y�Լ����Һ���x���滻y ����yΪr��˫��


                    nextNode.leftChildNode=deleteNode.leftChildNode; //�ú�̽ڵ�y����Ϊ����������
                    nextNode.rightChildNode=deleteNode.rightChildNode; //��̽ڵ��Ϊr(z��������)��˫�׽ڵ�

                    if(z.leftChildNode==deleteNode){
                        z.leftChildNode=nextNode;
                    }else{
                        z.rightChildNode=nextNode;
                    }
                }
            }*/
    }


    public static void main(String[] args) {
        int[] treeNode=new int[]{
                5,4,9,7,8
        };
        BinarySearchTree binarySearchTree=new BinarySearchTree();
        for (int i = 0; i < treeNode.length; i++) {
            //binarySearchTree.buildByRecursion(binarySearchTree.root,treeNode[i]);
            binarySearchTree.buildByIteration(binarySearchTree.root,treeNode[i]);
        }
        /*TreeNode findNode=binarySearchTree.search(binarySearchTree.root,2);
        TreeNode findNode1=binarySearchTree.searchIterative(binarySearchTree.root,5);
        System.out.println(findNode.rightChildNode.threeDate);
        System.out.println("���Ԫ�أ� "+binarySearchTree.findMax().threeDate);
        System.out.println("��СԪ�أ� "+binarySearchTree.findMin().threeDate);*/

        boolean isExist=binarySearchTree.search(binarySearchTree.root,5);
        System.out.println(isExist);

        /*int data=6;
        System.out.println("��̣� "+
                binarySearchTree.getNext(binarySearchTree.searchIterative(binarySearchTree.root,data))
                        .threeDate);

        System.out.println("ǰ���� "+
                binarySearchTree.getPre(binarySearchTree.searchIterative(binarySearchTree.root,data))
                        .threeDate);*/


        binarySearchTree.delete(binarySearchTree,binarySearchTree.searchIterative(binarySearchTree.root,5));
    }

}

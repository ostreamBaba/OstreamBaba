package com.dataStructure.nowCoder;

import java.util.Arrays;

/**
 * @ Create by ostreamBaba on 18-8-6
 * @ ����
 */

public class _5 {
    public static final int[] xRow={0,0,1,-1};
    public static final int[] yRow={1,-1,0,0};
    public static boolean[] visited;
    public static int n=0;
    public static int m=0;
    public static int length=0;
    public static boolean flag=false;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        n=rows;
        m=cols;
        length=str.length;
        visited=new boolean[(rows+1)*(cols+1)];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i*m+j]==str[0]){
                    dfs(i,j,0,matrix,str);
                    if(flag){
                        flag=false;
                        return true;
                    }
                    Arrays.fill(visited,false);
                }
            }
        }
        return false;
    }

    public void dfs(int x,int y,int pos,char[] matrix,char[] str){
        if(x<0||x>=n||y<0||y>=m||str[pos]!=matrix[x*m+y]||visited[x*m+y]){ //��֦ �ж�Խ����߷��ʹ����ߵ���str[pos]��ֵ
            return;
        }
        if(pos==length-1){ //��������
            flag=true;
        }
        if(flag){ //��֦
            return;
        }
        visited[x*m+y]=true; //�������� ����Ϊ���ʹ�
        for (int i = 0; i < 4; i++) { //����һ��������ĸ���������
            dfs(x+xRow[i],y+yRow[i], pos + 1,matrix, str);
        }
        visited[x*m+y]=false; //����
    }

    public static void main(String[] args) {
        String s1="AAAAAAAAAAAA";
        String s2="AAAAAAAAAAAA";
        char[] matrix=s1.toCharArray();
        char[] str=s2.toCharArray();
        System.out.println(new _5().hasPath(matrix,3,4,str));
    }

}

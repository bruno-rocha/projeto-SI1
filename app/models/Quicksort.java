/**
 * 
 */
package models;


import java.util.List;

public class Quicksort {

	public static void sort(List<Dica> dicas, int left, int right) {

        int m = partition(dicas, 0, dicas.size());

		sort(dicas,0,m-1);

		sort(dicas,m+1,dicas.size());
		
	}
	
	private static int partition(List<Dica> dicas, int left, int right) {

		 int i=left+1; 

		 int j=right; 

		 Dica pivot = dicas.get(left);

		 while (i<=j) { 

		 if ((dicas.get(i).getNumeroConcordancias()) <= (pivot.getNumeroConcordancias())) i++;

		 else if (dicas.get(j).getNumeroConcordancias() > (pivot.getNumeroConcordancias())) j--;

         else {
             Dica t = dicas.get(i);
             dicas.set(i, dicas.get(j));
             dicas.set(j, t);
         }


		 }


        Dica t = dicas.get(left);
        dicas.set(left, dicas.get(j));
        dicas.set(j, t);

        return j;

		}

}

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
public class Trpaslici{

    public static int pocetMoznosti(int n){
        ArrayList<Integer> delitele = new ArrayList<Integer>();
        ArrayList<Integer> delitele_revesed = new ArrayList<Integer>();
        for (int i=1; i<=Math.sqrt(n); i++){
            if (n%i==0) {
                if (n / i == i) {
                    delitele.add(i);
                    delitele_revesed.add(i);
                } else {// Otherwise print both
                    delitele.add(i);
                    delitele.add(n/i);
                    delitele_revesed.add(i);
                    delitele_revesed.add(n/i);
                }
            }
        }
        Collections.sort(delitele_revesed,Collections.reverseOrder());
        Collections.sort(delitele);
        ArrayList<ArrayList<Integer>> final_list = new ArrayList<ArrayList<Integer>>();

        for(int j = 0;j < delitele.size();j++){ // hor index
            for(int k = 0;k < (delitele.size()+1)/2;k++){ // dol index
                if(delitele_revesed.get(j) * delitele.get(k) > n) {
                    break;
                }


                    for (int l = 0 ; l < (delitele.size()+1)/2; l++) {

                        if (delitele_revesed.get(j) * delitele.get(k) * delitele.get(l) == n) {
                            ArrayList<Integer> list2 = new ArrayList<Integer>(3);
                            list2.add(delitele_revesed.get(j));
                            list2.add(delitele.get(k));
                            list2.add(delitele.get(l));

                            //arr.add(list2);
                            Collections.sort(list2);//,Collections.reverseOrder()
                            if(final_list.contains(list2) ){
                            }else {
                                final_list.add(list2);
                            }
                        }
                    }
            }
        }

        return final_list.size();
    }
    public static int rozdiel(int n){

        ArrayList<Integer> delitele = new ArrayList<Integer>();
        ArrayList<Integer> delitele_revesed = new ArrayList<Integer>();
        for (int i=1; i<=Math.sqrt(n); i++){
            if (n%i==0) {
                if (n / i == i) {
                    delitele.add(i);
                    delitele_revesed.add(i);
                } else {// Otherwise print both
                    delitele.add(i);
                    delitele.add(n/i);
                    delitele_revesed.add(i);
                    delitele_revesed.add(n/i);
                }
            }
        }
        Collections.sort(delitele_revesed,Collections.reverseOrder());
        Collections.sort(delitele);
        /*int max = 2*(1*1+1*n+n*1);
        for(int i  = 0 ; i < delitele.size()-1 ;i++){
            for(int k  = 0 ; k < delitele.size()-1 ;k++){
                for(int l  = 0 ; l < delitele.size()-1 ;l++){
                    if(delitele.get(i) * delitele.get(k)  * delitele.get(l) == n){
                        int min = 2*(delitele.get(i)*delitele.get(k)+
                                delitele.get(k)*delitele.get(l)+
                                delitele.get(l)*delitele.get(i));
                        return max-min;
                    }
                }
            }
        }
        return 0;*/
        ArrayList<ArrayList<Integer>> final_list = new ArrayList<ArrayList<Integer>>();
        long max = 0;//2*(1*1+1*n+n*1);
        long comper_min;
        long comper_max;
        long min = Integer.MAX_VALUE;//2*(final_list.get(final_list.size()-1).get(0)*final_list.get(final_list.size()-1).get(1)+final_list.get(final_list.size()-1).get(1)
                //*final_list.get(final_list.size()-1).get(2)+final_list.get(final_list.size()-1).get(2)*final_list.get(final_list.size()-1).get(0));
        for(int j = 0;j < delitele.size();j++){ // hor index
            for(int k = 0;k < (delitele.size()+1)/2;k++){ // dol index
                if(delitele_revesed.get(j) * delitele.get(k) > n) {
                    break;
                }


                for (int l = 0 ; l < (delitele.size()+1)/2; l++) {

                    if (delitele_revesed.get(j) * delitele.get(k) * delitele.get(l) == n) {
                        ArrayList<Integer> list2 = new ArrayList<Integer>(3);
                        list2.add(delitele_revesed.get(j));
                        list2.add(delitele.get(k));
                        list2.add(delitele.get(l));

                        //arr.add(list2);
                        Collections.sort(list2);//,Collections.reverseOrder()
                        if(final_list.contains(list2) ){
                        }else {
                            comper_min = 2*(delitele_revesed.get(j) * delitele.get(k)+
                                    delitele.get(k)*delitele.get(l)+
                                    delitele.get(l)*delitele_revesed.get(j));
                            comper_max = 2*(delitele_revesed.get(j) * delitele.get(k)+
                                    delitele.get(k)*delitele.get(l)+
                                    delitele.get(l)*delitele_revesed.get(j));
                            if( comper_min < min){
                                min = comper_min;
                                /*System.out.println(delitele_revesed.get(j) +"    "+
                                        delitele.get(k)+"    "+
                                        delitele.get(l));*/
                            }
                            if( comper_max > max){
                                max = comper_max;
                                /*System.out.println(delitele_revesed.get(j) +"    "+
                                        delitele.get(k)+"    "+
                                        delitele.get(l));*/
                            }
                            final_list.add(list2);
                        }
                    }
                }
            }
        }
        /*Collections.sort(final_list, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });*/
        //System.out.println(min);


        return (int)( max - min);

    }
    public static void main(String[] args) {

        //System.out.println("VYSLEDOK "+pocetMoznosti(2147483646));
        System.out.println("VYSLEDOK "+pocetMoznosti(36));
        System.out.println("VYSLEDOK "+rozdiel(4 ));
        /*System.out.println("VYSLEDOK "+pocetMoznosti(1));
        System.out.println("VYSLEDOK "+pocetMoznosti(7));
        System.out.println("VYSLEDOK "+pocetMoznosti(5));
        System.out.println("VYSLEDOK "+pocetMoznosti(13));*/
        //System.out.println("VYSLEDOK "+pocetMoznosti(2147483647));
        //System.out.println(sucet(new int[]{11, 16, 3, 17}, 22));
    }
}

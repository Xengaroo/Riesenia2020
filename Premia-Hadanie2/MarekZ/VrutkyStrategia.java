public class VrutkyStrategia {

    /*
    Toto je problem dinosaurich vajec ked mame 2 vajcia. Dokazeme si vypocitat max potrebny pocet pokusov pre dane
    spodne a horne ohranicenie. Potom strategia je hadat take nove spodne ohranicenie, aby v pripade ze je odpoved
    false sme vedeli postupne iterovanim prehladat cisla od aktualneho spodneho ohranicenia po to ktore dalo false.
     */
    public static int maxTries(int lb, int ub)
    {
        int i = (ub-lb+1) * 2;
        double mt = Math.sqrt(i);
        mt = Math.floor(mt);
        if((mt + (mt*(mt-1))/2) >= (ub-lb))
            return (int)mt;
        else
            return 1 + (int)mt;
    }

    public static void strategia(Vrutky m) {
        int ub = m.doKolkoHrame();
        int lb = 1;
        boolean isover = true;
        int mt=0;
        while(isover)
        {
            mt = maxTries(lb, ub);
            isover = m.jeVacsieAko(lb-1+mt);
            if(isover)
                lb += mt;
        }
        for(int c = lb; c < lb-1+mt; c++)
        {
            if(m.jeVacsieAko(c) == false)
            {
                m.jetoCislo(c);
                return;
            }
        }
        m.jetoCislo(lb-1+mt);
    }

    public static void main(String[] args) {
        Vrutky m = new Vrutky();
        strategia(m);
        System.out.println(m.kolkoOtazok() + " <= " + maxTries(1, m.doKolkoHrame()));
    }
}

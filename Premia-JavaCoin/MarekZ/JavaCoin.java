import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaCoin {

    public static class DictItem
    {
        String meno;
        int int5;
        long long6;
        String string6;
        public DictItem(String meno, int int5, long long6, String string6)
        {
            this.meno = meno;
            this.int5 = int5;
            this.long6 = long6;
            this.string6 = string6;
        }
    }

    public static final DictItem [] dict =
    {
        //new DictItem( "Zitnik", 1634844, 40162307, "IKLOX" ),
        //new DictItem( "Borovansky", 95743, 33370284, "BPBCHB")
    };


    private static int int5 = 0;

    public static DictItem findName(String name)
    {
        for(int i = 0; i < dict.length; i ++) {
            if (dict[i].meno == name)
                return dict[i];
        }
        return null;
    }

    public static String MD5(String str)  {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String mojePriezvisko() { return "Zitnik"; }
    public static int minInt5() //1634844
    {
        DictItem di = findName(mojePriezvisko());
        if(di != null)
            return di.int5;

        String md5;
        for(int i = 0; i < Integer.MAX_VALUE; i ++)
        {
            md5 = MD5(mojePriezvisko() + Integer.toString(i));
            if(md5.startsWith("00000")) {
                int5 = i;
                return i;
            }
        }
        return 0;
    }

    public static long minLong6() //40162307
    {
        DictItem di = findName(mojePriezvisko());
        if(di != null)
            return di.long6;

        String md5;
        for(long i = int5; i < Long.MAX_VALUE; i ++)
        {
            md5 = MD5(mojePriezvisko() + Long.toString(i));
            if(md5.startsWith("000000"))
                return i;
        }
        return 0;
    }

    public static String minString6()   //IKLOX
    {
        DictItem di = findName(mojePriezvisko());
        if(di != null)
            return di.string6;

        StringBuffer retv = new StringBuffer("A");
        String md5 = MD5(mojePriezvisko().concat(retv.toString()));
        while(! md5.startsWith("000000"))
        {
            //System.out.println(retv.toString());
            for(int i = retv.length() - 1; i >= 0; i --)
            {
                char e = retv.charAt(i);
                if(e == 'Z') {
                    retv.setCharAt(i, 'A');
                    if (i == 0)
                        retv.append('A');
                }
                else
                {
                    retv.setCharAt(i, ++e);
                    break;
                }
            }
            md5 = MD5(mojePriezvisko().concat(retv.toString()));
        }
        return retv.toString();
    }

    public static void main(String[] args) {
        System.out.println(minInt5());
        System.out.println(minLong6());
        System.out.println(minString6());
    }
}
 
package com.cmeon.nfchomeauto;
import java.util.HashMap;
/**
 * Created by cmeon on 24/07/14.
 */
public class Data {
    public static final String[] movieIds = {
            "tt0120903", "tt0311429",
            "tt0903747", "tt1204975",
            "tt1403865", "tt1826590",
            "tt2239832", "tt1621045"
    };
    public static final String[] musicIds = {
            "tt012090", "tt031142"
    };
    // references to our images
    public static final Integer[] musicThumbIds = {
            R.drawable.shakira, R.drawable.pitbull
    };

    // references to our images
    private final String[] movieInfoIds = {
            "baby dadd czkalblicuhgoudbnf k.mlkdjfoi jhzgdjfgv u8zhljfn zjdfiu vhzduih ;znjid  j uhpobudh jzdnfpiph9ishd fy" +
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "ani afjlbhazciulbzajafd nba;ijdb hf;hujpzduifh uzbd;h.ovlzjdbiulozgudbjlzdhbiup9abz9zsdf zijbdiu ta "+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "wahalad azdlj abuc adflivau ;vajcpiajbidvahd vaisd vpuiaisdgpva9 h;idv hy09[y8fvbaz udhgv8 pzadvhpadvasdv ad zdf avrat"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "john naakld jbov absjdnv paijhbdpv ainbvp andvnapjnv janpu hpa dvpahiv aihduiv aphzivn ljnv0a8peiwy9pav8yv pier"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "ili0a ijnij divaihd vabpjbo ao bdviuhaij dnvijanbpijdvhandvj phaijvhabvoiuagbd;lvapjhdiudba ubpia duza "+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "l kkajpnb iu iabsc sa zdlbodbucbpzdiauhbf ipubanf janfij npiauphd9uvf ha dn;piahij hpa hijahpijhdzijk npjzndc jzdivcs"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            " aksdjivlijabsdpiv avav asdva albdhuvbgoa dbau dchbaihgpisd paivda uhdvpuiah ;jdvaobv japiudczdva dv"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara",
            "asijvbdalgd vpab sduvbail hguiap obv zndochubzadub pizdahuf ;adinf adzlab dlajhvjbvlabnphvjab aohnba nbadh lancija n;auh8va"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"+
                    "jabkdjbfkajshdfljakuhsdfjahsdkjhfajoshdifj oadof dbfaouh doajndovhuaobdnvjaibodfijvnadjifoajdnvoajibo vjanojvba v" +
                    "ajdb abdovjhaodnval idbonvajonbvuhabodvaobvjanbldiuvhap fv8na0udhca n0f 8eqara"
    };

    // references to our images
    private final float[] movieRatingsIds = {
            7.5f, 3.2f, 3.8f, 1.6f,
            9.9f, 7.8f, 7.8f, 6.9f
    };

    // references to our images
    private final float[] musicRatingsIds = {
            7.5f, 3.2f, 3.8f, 1.6f,
            9.9f, 7.8f, 7.8f, 6.9f
    };


    public float getRating(String type, Integer pos) {
         float rating = 0;
         if (type.equals("music"))
             rating = musicRatingsIds[pos];
         else if (type.equals("movie"))
             rating = movieRatingsIds[pos];
         return rating;
    }

    public String getInfo(Integer pos) {
        return movieInfoIds[pos];
    }

    public static String getIDInfo(String tagID) {
        String info = " ";
        HashMap hm = new HashMap();
        // Put elements to the map
        hm.put("043D2E8A092980", "action stop");
        hm.put("0457228A092980", "action pause");
        hm.put("046A228A092980", "torch on");
        hm.put("04512E8A092980", "torch off");
        hm.put("042AD3415B2380", "video tt0120903");
        if (hm.get(tagID) != null) {
            info = hm.get(tagID).toString();
        }
        return info;
    }

}

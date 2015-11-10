package compute;

/**
 * Created by gauth_000 on 10-Nov-15.
 */
import models.Grammar;
import models.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class FirstFollow
{
    static char ntermnl[],termnl[];
    static int ntlen,tlen;
    public static String grmr[][],fst[],flw[];
    private Grammar grammar;

    public FirstFollow(Grammar grammar)
    {
        this.grammar=grammar;

        try
        {
            String nt,t;
            int i,j,n;
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            String nonTerminals="";
            for (Symbol symbol:grammar.nonTerminals)
                nonTerminals+=symbol.val;
            nt=nonTerminals;
            ntlen=nt.length();
            ntermnl=new char[ntlen];
            ntermnl=nt.toCharArray();

            String terminals="";
            for (Symbol symbol:grammar.terminals)
                terminals+=symbol.val;
            t=terminals;
            tlen=t.length();
            termnl=new char[tlen];
            termnl=t.toCharArray();

            grmr=new String[ntlen][];
            for(i=0;i<ntlen;i++)
            {
                n=grammar.nonTerminals.get(i).getProductions().size();
                grmr[i]=new String[n];
                for(j=0;j<n;j++)
                {
                    String production="";
                    for (Symbol symbol:grammar.nonTerminals.get(i).getProductions().get(j))
                        production+=symbol.val;
                    grmr[i][j]=production;
                }
            }

//            fst=new String[ntlen];
//            for(i=0;i<ntlen;i++)
//            {
//                fst[i]=first(i);
//            }
//            for(i=0;i<ntlen;i++)
//                flw=new String[ntlen];
//            for(i=0;i<ntlen;i++)
//                flw[i]=follow(i);
//            for(i=0;i<ntlen;i++)
//                flw[i]=removeDuplicates(flw[i]);

            fst=new String[ntlen];
            for(i=0;i<ntlen;i++)
                fst[i]=first(i);
            System.out.println("First Set");
            for(i=0;i<ntlen;i++)
            {
                System.out.println(grammar.nonTerminals.get(i)+":"+removeDuplicates(fst[i]));
                fst[i]=removeDuplicates(fst[i]);
            }
            flw=new String[ntlen];
            for(i=0;i<ntlen;i++)
                flw[i]=follow(i);
            System.out.println("Follow Set");
            for(i=0;i<ntlen;i++)
            {
                System.out.println(grammar.nonTerminals.get(i)+":"+removeDuplicates(flw[i]));
                flw[i]=removeDuplicates(flw[i]);
            }
        }
        catch (Exception e) {}
    }

    static String first(int i)
    {
        int j,k,l=0,found=0;
        String temp="",str="";
        for(j=0;j<grmr[i].length;j++) //number of productions
        {
            for(k=0;k<grmr[i][j].length();k++,found=0) //when nonterminal has epsilon production
            {
                for(l=0;l<ntlen;l++) //finding nonterminal
                {
                    if(grmr[i][j].charAt(k)==ntermnl[l]) //for nonterminal in first set
                    {
                        str=first(l);
                        if(!(str.length()==1 && str.charAt(0)=='!')) //when epsilon production is the only nonterminal production
                        temp=temp+str;
                        found=1;
                        break;
                    }
                }
                if(found==1)
                {
                    if(str.contains("!")) //here epsilon will lead to next nonterminal's first set
                        continue;
                }
                else //if first set includes terminal
                    temp=temp+grmr[i][j].charAt(k);
                break;
            }
        }
        return temp;
    }

    static String follow(int i)
    {
        char pro[],chr[];
        String temp="";
        int j,k,l,m,n,found=0;
        if(i==0)
            temp="$";
        for(j=0;j<ntlen;j++)
        {
            for(k=0;k<grmr[j].length;k++) //entering grammar matrix
            {
                pro=new char[grmr[j][k].length()];
                pro=grmr[j][k].toCharArray();
                for(l=0;l<pro.length;l++) //entering each production
                {
                    if(pro[l]==ntermnl[i]) //finding the nonterminal whose follow set is to be found
                    {
                        if(l==pro.length-1) //if it is the last terminal/non-terminal then follow of current non-terminal
                        {
                            if(j<i)
                                temp=temp+flw[j];
                        }
                        else
                        {
                            for(m=0;m<ntlen;m++)
                            {
                                if(pro[l+1]==ntermnl[m]) //first of next non-terminal otherwise (else later…)
                                {
                                    chr=new char[fst[m].length()];
                                    chr=fst[m].toCharArray();
                                    for(n=0;n<chr.length;n++)
                                    {
                                        if(chr[n]=='!') //if first inclu!des epsilon
                                        {
                                            if(l+1==pro.length-1)
                                                temp=temp+follow(j); //when non-terminal is second last
                                            else
                                                temp=temp+follow(m);
                                        }
                                        else
                                        temp=temp+chr[n]; //include whole first set except epsilon
                                    }
                                    found=1;
                                }
                            }
                            if(found!=1)
                                temp=temp+pro[l+1]; //follow set will include terminal(else is here)
                        }
                    }
                }
            }
        }
        return temp;
    }
    static String removeDuplicates(String str)
    {
        int i;
        char ch;
        boolean seen[] = new boolean[256];
        StringBuilder sb = new StringBuilder(seen.length);
        for(i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if (!seen[ch])
            {
                seen[ch] = true;
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
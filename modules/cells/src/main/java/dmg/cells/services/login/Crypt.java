package dmg.cells.services.login;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Crypt {

   private static final long [] [] SPtrans = {
      /* nibble 0 */
    { 0x00820200L, 0x00020000L, 0x80800000L, 0x80820200L,
      0x00800000L, 0x80020200L, 0x80020000L, 0x80800000L,
      0x80020200L, 0x00820200L, 0x00820000L, 0x80000200L,
      0x80800200L, 0x00800000L, 0x00000000L, 0x80020000L,
      0x00020000L, 0x80000000L, 0x00800200L, 0x00020200L,
      0x80820200L, 0x00820000L, 0x80000200L, 0x00800200L,
      0x80000000L, 0x00000200L, 0x00020200L, 0x80820000L,
      0x00000200L, 0x80800200L, 0x80820000L, 0x00000000L,
      0x00000000L, 0x80820200L, 0x00800200L, 0x80020000L,
      0x00820200L, 0x00020000L, 0x80000200L, 0x00800200L,
      0x80820000L, 0x00000200L, 0x00020200L, 0x80800000L,
      0x80020200L, 0x80000000L, 0x80800000L, 0x00820000L,
      0x80820200L, 0x00020200L, 0x00820000L, 0x80800200L,
      0x00800000L, 0x80000200L, 0x80020000L, 0x00000000L,
      0x00020000L, 0x00800000L, 0x80800200L, 0x00820200L,
      0x80000000L, 0x80820000L, 0x00000200L, 0x80020200L},
      /* nibble 1 */
    { 0x10042004L, 0x00000000L, 0x00042000L, 0x10040000L,
      0x10000004L, 0x00002004L, 0x10002000L, 0x00042000L,
      0x00002000L, 0x10040004L, 0x00000004L, 0x10002000L,
      0x00040004L, 0x10042000L, 0x10040000L, 0x00000004L,
      0x00040000L, 0x10002004L, 0x10040004L, 0x00002000L,
      0x00042004L, 0x10000000L, 0x00000000L, 0x00040004L,
      0x10002004L, 0x00042004L, 0x10042000L, 0x10000004L,
      0x10000000L, 0x00040000L, 0x00002004L, 0x10042004L,
      0x00040004L, 0x10042000L, 0x10002000L, 0x00042004L,
      0x10042004L, 0x00040004L, 0x10000004L, 0x00000000L,
      0x10000000L, 0x00002004L, 0x00040000L, 0x10040004L,
      0x00002000L, 0x10000000L, 0x00042004L, 0x10002004L,
      0x10042000L, 0x00002000L, 0x00000000L, 0x10000004L,
      0x00000004L, 0x10042004L, 0x00042000L, 0x10040000L,
      0x10040004L, 0x00040000L, 0x00002004L, 0x10002000L,
      0x10002004L, 0x00000004L, 0x10040000L, 0x00042000L},
      /* nibble 2 */
    { 0x41000000L, 0x01010040L, 0x00000040L, 0x41000040L,
      0x40010000L, 0x01000000L, 0x41000040L, 0x00010040L,
      0x01000040L, 0x00010000L, 0x01010000L, 0x40000000L,
      0x41010040L, 0x40000040L, 0x40000000L, 0x41010000L,
      0x00000000L, 0x40010000L, 0x01010040L, 0x00000040L,
      0x40000040L, 0x41010040L, 0x00010000L, 0x41000000L,
      0x41010000L, 0x01000040L, 0x40010040L, 0x01010000L,
      0x00010040L, 0x00000000L, 0x01000000L, 0x40010040L,
      0x01010040L, 0x00000040L, 0x40000000L, 0x00010000L,
      0x40000040L, 0x40010000L, 0x01010000L, 0x41000040L,
      0x00000000L, 0x01010040L, 0x00010040L, 0x41010000L,
      0x40010000L, 0x01000000L, 0x41010040L, 0x40000000L,
      0x40010040L, 0x41000000L, 0x01000000L, 0x41010040L,
      0x00010000L, 0x01000040L, 0x41000040L, 0x00010040L,
      0x01000040L, 0x00000000L, 0x41010000L, 0x40000040L,
      0x41000000L, 0x40010040L, 0x00000040L, 0x01010000L},
      /* nibble 3 */
    { 0x00100402L, 0x04000400L, 0x00000002L, 0x04100402L,
      0x00000000L, 0x04100000L, 0x04000402L, 0x00100002L,
      0x04100400L, 0x04000002L, 0x04000000L, 0x00000402L,
      0x04000002L, 0x00100402L, 0x00100000L, 0x04000000L,
      0x04100002L, 0x00100400L, 0x00000400L, 0x00000002L,
      0x00100400L, 0x04000402L, 0x04100000L, 0x00000400L,
      0x00000402L, 0x00000000L, 0x00100002L, 0x04100400L,
      0x04000400L, 0x04100002L, 0x04100402L, 0x00100000L,
      0x04100002L, 0x00000402L, 0x00100000L, 0x04000002L,
      0x00100400L, 0x04000400L, 0x00000002L, 0x04100000L,
      0x04000402L, 0x00000000L, 0x00000400L, 0x00100002L,
      0x00000000L, 0x04100002L, 0x04100400L, 0x00000400L,
      0x04000000L, 0x04100402L, 0x00100402L, 0x00100000L,
      0x04100402L, 0x00000002L, 0x04000400L, 0x00100402L,
      0x00100002L, 0x00100400L, 0x04100000L, 0x04000402L,
      0x00000402L, 0x04000000L, 0x04000002L, 0x04100400L},
      /* nibble 4 */
    { 0x02000000L, 0x00004000L, 0x00000100L, 0x02004108L,
      0x02004008L, 0x02000100L, 0x00004108L, 0x02004000L,
      0x00004000L, 0x00000008L, 0x02000008L, 0x00004100L,
      0x02000108L, 0x02004008L, 0x02004100L, 0x00000000L,
      0x00004100L, 0x02000000L, 0x00004008L, 0x00000108L,
      0x02000100L, 0x00004108L, 0x00000000L, 0x02000008L,
      0x00000008L, 0x02000108L, 0x02004108L, 0x00004008L,
      0x02004000L, 0x00000100L, 0x00000108L, 0x02004100L,
      0x02004100L, 0x02000108L, 0x00004008L, 0x02004000L,
      0x00004000L, 0x00000008L, 0x02000008L, 0x02000100L,
      0x02000000L, 0x00004100L, 0x02004108L, 0x00000000L,
      0x00004108L, 0x02000000L, 0x00000100L, 0x00004008L,
      0x02000108L, 0x00000100L, 0x00000000L, 0x02004108L,
      0x02004008L, 0x02004100L, 0x00000108L, 0x00004000L,
      0x00004100L, 0x02004008L, 0x02000100L, 0x00000108L,
      0x00000008L, 0x00004108L, 0x02004000L, 0x02000008L},
      /* nibble 5 */
    { 0x20000010L, 0x00080010L, 0x00000000L, 0x20080800L,
      0x00080010L, 0x00000800L, 0x20000810L, 0x00080000L,
      0x00000810L, 0x20080810L, 0x00080800L, 0x20000000L,
      0x20000800L, 0x20000010L, 0x20080000L, 0x00080810L,
      0x00080000L, 0x20000810L, 0x20080010L, 0x00000000L,
      0x00000800L, 0x00000010L, 0x20080800L, 0x20080010L,
      0x20080810L, 0x20080000L, 0x20000000L, 0x00000810L,
      0x00000010L, 0x00080800L, 0x00080810L, 0x20000800L,
      0x00000810L, 0x20000000L, 0x20000800L, 0x00080810L,
      0x20080800L, 0x00080010L, 0x00000000L, 0x20000800L,
      0x20000000L, 0x00000800L, 0x20080010L, 0x00080000L,
      0x00080010L, 0x20080810L, 0x00080800L, 0x00000010L,
      0x20080810L, 0x00080800L, 0x00080000L, 0x20000810L,
      0x20000010L, 0x20080000L, 0x00080810L, 0x00000000L,
      0x00000800L, 0x20000010L, 0x20000810L, 0x20080800L,
      0x20080000L, 0x00000810L, 0x00000010L, 0x20080010L},
      /* nibble 6 */
    { 0x00001000L, 0x00000080L, 0x00400080L, 0x00400001L,
      0x00401081L, 0x00001001L, 0x00001080L, 0x00000000L,
      0x00400000L, 0x00400081L, 0x00000081L, 0x00401000L,
      0x00000001L, 0x00401080L, 0x00401000L, 0x00000081L,
      0x00400081L, 0x00001000L, 0x00001001L, 0x00401081L,
      0x00000000L, 0x00400080L, 0x00400001L, 0x00001080L,
      0x00401001L, 0x00001081L, 0x00401080L, 0x00000001L,
      0x00001081L, 0x00401001L, 0x00000080L, 0x00400000L,
      0x00001081L, 0x00401000L, 0x00401001L, 0x00000081L,
      0x00001000L, 0x00000080L, 0x00400000L, 0x00401001L,
      0x00400081L, 0x00001081L, 0x00001080L, 0x00000000L,
      0x00000080L, 0x00400001L, 0x00000001L, 0x00400080L,
      0x00000000L, 0x00400081L, 0x00400080L, 0x00001080L,
      0x00000081L, 0x00001000L, 0x00401081L, 0x00400000L,
      0x00401080L, 0x00000001L, 0x00001001L, 0x00401081L,
      0x00400001L, 0x00401080L, 0x00401000L, 0x00001001L},
      /* nibble 7 */
    { 0x08200020L, 0x08208000L, 0x00008020L, 0x00000000L,
      0x08008000L, 0x00200020L, 0x08200000L, 0x08208020L,
      0x00000020L, 0x08000000L, 0x00208000L, 0x00008020L,
      0x00208020L, 0x08008020L, 0x08000020L, 0x08200000L,
      0x00008000L, 0x00208020L, 0x00200020L, 0x08008000L,
      0x08208020L, 0x08000020L, 0x00000000L, 0x00208000L,
      0x08000000L, 0x00200000L, 0x08008020L, 0x08200020L,
      0x00200000L, 0x00008000L, 0x08208000L, 0x00000020L,
      0x00200000L, 0x00008000L, 0x08000020L, 0x08208020L,
      0x00008020L, 0x08000000L, 0x00000000L, 0x00208000L,
      0x08200020L, 0x08008020L, 0x08008000L, 0x00200020L,
      0x08208000L, 0x00000020L, 0x00200020L, 0x08008000L,
      0x08208020L, 0x00200000L, 0x08200000L, 0x08000020L,
      0x00208000L, 0x00008020L, 0x08008020L, 0x08200000L,
      0x00000020L, 0x08208000L, 0x00208020L, 0x00000000L,
      0x08000000L, 0x08200020L, 0x00008000L, 0x00208020L }
   };
   private static final long [] [] skb = {
      /* for C bits (numbered as per FIPS 46) 1 2 3 4 5 6 */
   {  0x00000000L,0x00000010L,0x20000000L,0x20000010L,
      0x00010000L,0x00010010L,0x20010000L,0x20010010L,
      0x00000800L,0x00000810L,0x20000800L,0x20000810L,
      0x00010800L,0x00010810L,0x20010800L,0x20010810L,
      0x00000020L,0x00000030L,0x20000020L,0x20000030L,
      0x00010020L,0x00010030L,0x20010020L,0x20010030L,
      0x00000820L,0x00000830L,0x20000820L,0x20000830L,
      0x00010820L,0x00010830L,0x20010820L,0x20010830L,
      0x00080000L,0x00080010L,0x20080000L,0x20080010L,
      0x00090000L,0x00090010L,0x20090000L,0x20090010L,
      0x00080800L,0x00080810L,0x20080800L,0x20080810L,
      0x00090800L,0x00090810L,0x20090800L,0x20090810L,
      0x00080020L,0x00080030L,0x20080020L,0x20080030L,
      0x00090020L,0x00090030L,0x20090020L,0x20090030L,
      0x00080820L,0x00080830L,0x20080820L,0x20080830L,
      0x00090820L,0x00090830L,0x20090820L,0x20090830L },
      /* for C bits (numbered as per FIPS 46) 7 8 10 11 12 13 */
    { 0x00000000L,0x02000000L,0x00002000L,0x02002000L,
      0x00200000L,0x02200000L,0x00202000L,0x02202000L,
      0x00000004L,0x02000004L,0x00002004L,0x02002004L,
      0x00200004L,0x02200004L,0x00202004L,0x02202004L,
      0x00000400L,0x02000400L,0x00002400L,0x02002400L,
      0x00200400L,0x02200400L,0x00202400L,0x02202400L,
      0x00000404L,0x02000404L,0x00002404L,0x02002404L,
      0x00200404L,0x02200404L,0x00202404L,0x02202404L,
      0x10000000L,0x12000000L,0x10002000L,0x12002000L,
      0x10200000L,0x12200000L,0x10202000L,0x12202000L,
      0x10000004L,0x12000004L,0x10002004L,0x12002004L,
      0x10200004L,0x12200004L,0x10202004L,0x12202004L,
      0x10000400L,0x12000400L,0x10002400L,0x12002400L,
      0x10200400L,0x12200400L,0x10202400L,0x12202400L,
      0x10000404L,0x12000404L,0x10002404L,0x12002404L,
      0x10200404L,0x12200404L,0x10202404L,0x12202404L  } ,
      /* for C bits (numbered as per FIPS 46) 14 15 16 17 19 20 */
   {  0x00000000L,0x00000001L,0x00040000L,0x00040001L,
      0x01000000L,0x01000001L,0x01040000L,0x01040001L,
      0x00000002L,0x00000003L,0x00040002L,0x00040003L,
      0x01000002L,0x01000003L,0x01040002L,0x01040003L,
      0x00000200L,0x00000201L,0x00040200L,0x00040201L,
      0x01000200L,0x01000201L,0x01040200L,0x01040201L,
      0x00000202L,0x00000203L,0x00040202L,0x00040203L,
      0x01000202L,0x01000203L,0x01040202L,0x01040203L,
      0x08000000L,0x08000001L,0x08040000L,0x08040001L,
      0x09000000L,0x09000001L,0x09040000L,0x09040001L,
      0x08000002L,0x08000003L,0x08040002L,0x08040003L,
      0x09000002L,0x09000003L,0x09040002L,0x09040003L,
      0x08000200L,0x08000201L,0x08040200L,0x08040201L,
      0x09000200L,0x09000201L,0x09040200L,0x09040201L,
      0x08000202L,0x08000203L,0x08040202L,0x08040203L,
      0x09000202L,0x09000203L,0x09040202L,0x09040203L },
      /* for C bits (numbered as per FIPS 46) 21 23 24 26 27 28 */
   {  0x00000000L,0x00100000L,0x00000100L,0x00100100L,
      0x00000008L,0x00100008L,0x00000108L,0x00100108L,
      0x00001000L,0x00101000L,0x00001100L,0x00101100L,
      0x00001008L,0x00101008L,0x00001108L,0x00101108L,
      0x04000000L,0x04100000L,0x04000100L,0x04100100L,
      0x04000008L,0x04100008L,0x04000108L,0x04100108L,
      0x04001000L,0x04101000L,0x04001100L,0x04101100L,
      0x04001008L,0x04101008L,0x04001108L,0x04101108L,
      0x00020000L,0x00120000L,0x00020100L,0x00120100L,
      0x00020008L,0x00120008L,0x00020108L,0x00120108L,
      0x00021000L,0x00121000L,0x00021100L,0x00121100L,
      0x00021008L,0x00121008L,0x00021108L,0x00121108L,
      0x04020000L,0x04120000L,0x04020100L,0x04120100L,
      0x04020008L,0x04120008L,0x04020108L,0x04120108L,
      0x04021000L,0x04121000L,0x04021100L,0x04121100L,
      0x04021008L,0x04121008L,0x04021108L,0x04121108L },
      /* for D bits (numbered as per FIPS 46) 1 2 3 4 5 6 */
    { 0x00000000L,0x10000000L,0x00010000L,0x10010000L,
      0x00000004L,0x10000004L,0x00010004L,0x10010004L,
      0x20000000L,0x30000000L,0x20010000L,0x30010000L,
      0x20000004L,0x30000004L,0x20010004L,0x30010004L,
      0x00100000L,0x10100000L,0x00110000L,0x10110000L,
      0x00100004L,0x10100004L,0x00110004L,0x10110004L,
      0x20100000L,0x30100000L,0x20110000L,0x30110000L,
      0x20100004L,0x30100004L,0x20110004L,0x30110004L,
      0x00001000L,0x10001000L,0x00011000L,0x10011000L,
      0x00001004L,0x10001004L,0x00011004L,0x10011004L,
      0x20001000L,0x30001000L,0x20011000L,0x30011000L,
      0x20001004L,0x30001004L,0x20011004L,0x30011004L,
      0x00101000L,0x10101000L,0x00111000L,0x10111000L,
      0x00101004L,0x10101004L,0x00111004L,0x10111004L,
      0x20101000L,0x30101000L,0x20111000L,0x30111000L,
      0x20101004L,0x30101004L,0x20111004L,0x30111004L },
      /* for D bits (numbered as per FIPS 46) 8 9 11 12 13 14 */
    { 0x00000000L,0x08000000L,0x00000008L,0x08000008L,
      0x00000400L,0x08000400L,0x00000408L,0x08000408L,
      0x00020000L,0x08020000L,0x00020008L,0x08020008L,
      0x00020400L,0x08020400L,0x00020408L,0x08020408L,
      0x00000001L,0x08000001L,0x00000009L,0x08000009L,
      0x00000401L,0x08000401L,0x00000409L,0x08000409L,
      0x00020001L,0x08020001L,0x00020009L,0x08020009L,
      0x00020401L,0x08020401L,0x00020409L,0x08020409L,
      0x02000000L,0x0A000000L,0x02000008L,0x0A000008L,
      0x02000400L,0x0A000400L,0x02000408L,0x0A000408L,
      0x02020000L,0x0A020000L,0x02020008L,0x0A020008L,
      0x02020400L,0x0A020400L,0x02020408L,0x0A020408L,
      0x02000001L,0x0A000001L,0x02000009L,0x0A000009L,
      0x02000401L,0x0A000401L,0x02000409L,0x0A000409L,
      0x02020001L,0x0A020001L,0x02020009L,0x0A020009L,
      0x02020401L,0x0A020401L,0x02020409L,0x0A020409L },
      /* for D bits (numbered as per FIPS 46) 16 17 18 19 20 21 */
    { 0x00000000L,0x00000100L,0x00080000L,0x00080100L,
      0x01000000L,0x01000100L,0x01080000L,0x01080100L,
      0x00000010L,0x00000110L,0x00080010L,0x00080110L,
      0x01000010L,0x01000110L,0x01080010L,0x01080110L,
      0x00200000L,0x00200100L,0x00280000L,0x00280100L,
      0x01200000L,0x01200100L,0x01280000L,0x01280100L,
      0x00200010L,0x00200110L,0x00280010L,0x00280110L,
      0x01200010L,0x01200110L,0x01280010L,0x01280110L,
      0x00000200L,0x00000300L,0x00080200L,0x00080300L,
      0x01000200L,0x01000300L,0x01080200L,0x01080300L,
      0x00000210L,0x00000310L,0x00080210L,0x00080310L,
      0x01000210L,0x01000310L,0x01080210L,0x01080310L,
      0x00200200L,0x00200300L,0x00280200L,0x00280300L,
      0x01200200L,0x01200300L,0x01280200L,0x01280300L,
      0x00200210L,0x00200310L,0x00280210L,0x00280310L,
      0x01200210L,0x01200310L,0x01280210L,0x01280310L },
      /* for D bits (numbered as per FIPS 46) 22 23 24 25 27 28 */
    { 0x00000000L,0x04000000L,0x00040000L,0x04040000L,
      0x00000002L,0x04000002L,0x00040002L,0x04040002L,
      0x00002000L,0x04002000L,0x00042000L,0x04042000L,
      0x00002002L,0x04002002L,0x00042002L,0x04042002L,
      0x00000020L,0x04000020L,0x00040020L,0x04040020L,
      0x00000022L,0x04000022L,0x00040022L,0x04040022L,
      0x00002020L,0x04002020L,0x00042020L,0x04042020L,
      0x00002022L,0x04002022L,0x00042022L,0x04042022L,
      0x00000800L,0x04000800L,0x00040800L,0x04040800L,
      0x00000802L,0x04000802L,0x00040802L,0x04040802L,
      0x00002800L,0x04002800L,0x00042800L,0x04042800L,
      0x00002802L,0x04002802L,0x00042802L,0x04042802L,
      0x00000820L,0x04000820L,0x00040820L,0x04040820L,
      0x00000822L,0x04000822L,0x00040822L,0x04040822L,
      0x00002820L,0x04002820L,0x00042820L,0x04042820L,
      0x00002822L,0x04002822L,0x00042822L,0x04042822L }
   };
   private static final short [] con_salt = {
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
      0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,
      0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,
      0x0A,0x0B,0x05,0x06,0x07,0x08,0x09,0x0A,
      0x0B,0x0C,0x0D,0x0E,0x0F,0x10,0x11,0x12,
      0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1A,
      0x1B,0x1C,0x1D,0x1E,0x1F,0x20,0x21,0x22,
      0x23,0x24,0x25,0x20,0x21,0x22,0x23,0x24,
      0x25,0x26,0x27,0x28,0x29,0x2A,0x2B,0x2C,
      0x2D,0x2E,0x2F,0x30,0x31,0x32,0x33,0x34,
      0x35,0x36,0x37,0x38,0x39,0x3A,0x3B,0x3C,
      0x3D,0x3E,0x3F,0x00,0x00,0x00,0x00,0x00,
   };

   private static final short [] cov_2char = {
      0x2E,0x2F,0x30,0x31,0x32,0x33,0x34,0x35,
      0x36,0x37,0x38,0x39,0x41,0x42,0x43,0x44,
      0x45,0x46,0x47,0x48,0x49,0x4A,0x4B,0x4C,
      0x4D,0x4E,0x4F,0x50,0x51,0x52,0x53,0x54,
      0x55,0x56,0x57,0x58,0x59,0x5A,0x61,0x62,
      0x63,0x64,0x65,0x66,0x67,0x68,0x69,0x6A,
      0x6B,0x6C,0x6D,0x6E,0x6F,0x70,0x71,0x72,
      0x73,0x74,0x75,0x76,0x77,0x78,0x79,0x7A
   };
/*
#define c2l(c,l)	(l =((unsigned long)(*((c)++)))    , \
			 l|=((unsigned long)(*((c)++)))<< 8, \
			 l|=((unsigned long)(*((c)++)))<<16, \
			 l|=((unsigned long)(*((c)++)))<<24)

#define l2c(l,c)	(*((c)++)=(unsigned char)(((l)    )&0xff), \
			 *((c)++)=(unsigned char)(((l)>> 8)&0xff), \
			 *((c)++)=(unsigned char)(((l)>>16)&0xff), \
			 *((c)++)=(unsigned char)(((l)>>24)&0xff))

*/
public static String byteToHexString( byte b ) {
      String str = Integer.toHexString( ( b < 0 ) ? ( 256 + (int)b ) : (int)b  ) ;
      return str.length() == 1 ? '0' + str : str ;
   }
   public static String byteToHexString(byte [] bytes ) {
	      
		  StringBuilder sb = new StringBuilder(bytes.length +1);

       for (byte aByte : bytes) {
           sb.append(byteToHexString(aByte)).append(' ');
       }
	       return sb.toString() ;    
  }
/*
#define PERM_OP(a,b,t,n,m) ((t)=((((a)>>(n))^(b))&(m)),\
	(b)^=(t),\
	(a)^=((t)<<(n)))
	PERM_OP (d,c,t,4,0x0f0f0f0f);
	HPERM_OP(c,t,-2,0xcccc0000);
	HPERM_OP(d,t,-2,0xcccc0000);
	PERM_OP (d,c,t,1,0x55555555);
	PERM_OP (c,d,t,8,0x00ff00ff);
	PERM_OP (d,c,t,1,0x55555555);
#define HPERM_OP(a,t,n,m) ((t)=((((a)<<(16-(n)))^(a))&(m)),\
	(a)=(a)^(t)^(t>>(16-(n))))\
*/
   private static final int 
       shifts2[] = {0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0};
   private static final int ITERATIONS = 16 ;

   private void setKey( byte [] key , long [] schedule ){
       long c , d , t , s;
       long [] k = schedule ;
       c = (((long)key[0])&0xFFL) |
           ((((long)key[1])&0xFFL) <<  8 ) |
           ((((long)key[2])&0xFFL) << 16 ) |
           ((((long)key[3])&0xFFL) << 24 )  ;
       d = (((long)key[4])&0xFFL) |
           ((((long)key[5])&0xFFL) <<  8 ) |
           ((((long)key[6])&0xFFL) << 16 ) |
           ((((long)key[7])&0xFFL) << 24 )  ;

//      System.out.println( Long.toHexString(c) +":"+
//                          Long.toHexString(d) ) ;
       
       t  = (( d >>> 4 ) ^ c ) & 0x0f0f0f0fL ;
       c ^= t ;
       d ^= ( t << 4 ) ; 
//      System.out.println( Long.toHexString(c) +":"+
//                          Long.toHexString(d) ) ;
       
       t  = (( c << ( 16 - (-2) ) ) ^ c ) & 0xcccc0000L ;
       c  = c ^ t ^ ( t >>> ( 16 - (-2) ) ) ;
       
       t  = (( d << ( 16 - (-2) ) ) ^ d ) & 0xcccc0000L ;
       d  = d ^ t ^ ( t >>> ( 16 - (-2) ) ) ;
       
       t  = (( d >>> 1 ) ^ c ) & 0x55555555L ;
       c ^= t ;
       d ^= ( t << 1 ) ; 
       
       t  = (( c >>> 8 ) ^ d ) & 0x00ff00ffL ;
       d ^= t ;
       c ^= ( t << 8 ) ; 
       
       t  = (( d >>> 1 ) ^ c ) & 0x55555555L ;
       c ^= t ;
       d ^= ( t << 1 ) ; 

//    System.out.println( Long.toHexString(c) +":"+
//                          Long.toHexString(d) ) ;
     
       d = ((d&0x000000ffL)<<16)|(d&0x0000ff00L)|
           ((d&0x00ff0000L)>>>16)|((c&0xf0000000L)>>4) ;
           
       c &= 0x0fffffffL ;
       
//    System.out.println( Long.toHexString(c) +":"+
//                          Long.toHexString(d) ) ;

       int kc = 0 ;
       for( int i = 0 ; i < ITERATIONS ; i++){
          if( shifts2[i] != 0 ){ 
             c=((c>>>2)|(c<<26)); d=((d>>>2)|(d<<26)); 
          }else{ 
             c=((c>>>1)|(c<<27)); d=((d>>>1)|(d<<27)); 
          }
          c&=0x0fffffffL;
          d&=0x0fffffffL;

          s=	skb[0][(int)((c)&0x3f)]  |
	        skb[1][(int)(((c>>> 6)&0x03)|((c>>> 7)&0x3c))] |
	        skb[2][(int)(((c>>>13)&0x0f)|((c>>>14)&0x30))]|
	        skb[3][(int)(((c>>>20)&0x01)|
                             ((c>>>21)&0x06)|
			     ((c>>>22)&0x38))];
          t=	skb[4][(int)((d)&0x3f)]|
	        skb[5][(int)(((d>>> 7)&0x03)|
                             ((d>>> 8)&0x3c))]|
	        skb[6][(int)((d>>>15)&0x3f)]|
	        skb[7][(int)(((d>>>21)&0x0f)|
                             ((d>>>22)&0x30))];

          k[kc++] =((t<<16)|(s&0x0000ffffL))&0xffffffffL;
          s=       ((s>>>16)|(t&0xffff0000L));

          s=(s<<4)|(s>>>28);
          k[kc++] =s&0xffffffffL;
       }
//       for( int i = 0 ; i < 2*ITERATIONS ; i++){
//           System.out.println( "k["+i+"]="+Long.toHexString(k[i]) );
//      }
   }
   private static final int [][] xxx = {
    { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 } ,
    { 11 , 21 , 31 , 41 , 51 , 61 , 71 , 81 } ,
   
   } ;
   public String crypt( String salt , String keyString ){
       return new String(
                     crypt( (salt.substring(0,2).getBytes(StandardCharsets.UTF_8)) ,
                             keyString.getBytes(StandardCharsets.UTF_8)),
                     0 , 13  ) ;
   }
   public byte [] crypt( byte [] salt , byte [] keyString ){
   
       byte [] key = new byte[8] ;
       int i;
       for( i = 0 ; ( i < 8 ) && ( i < keyString.length ) ; i++ ) {
           key[i] = (byte) (keyString[i] << 1);
       }
       for( ; i < 8 ; i++ ) {
           key[i] = 0;
       }
        
//       System.out.println( byteToHexString( keyString ) ) ;   
//       System.out.println( byteToHexString( key ) ) ; 

       long [] schedule = new long[32] ;
       
       setKey( key , schedule ) ; 

       int x ;
       byte [] buff = new byte[20] ;
       
       x = buff[0] = (byte)( (salt[0] == '\0') ? 'A' : salt[0] );
       long Eswap0 = con_salt[x] ;
       x = buff[1] =(byte)((salt[1] == '\0')?'A':salt[1]);
       long Eswap1 = con_salt[x] << 4 ;
       
       long [] out = new long[2] ;
       
          
       body( out , schedule , Eswap0 , Eswap1 ) ;
       
//       System.out.println( 
//          "out[0]="+Long.toHexString(out[0])+
//          ";out[1]="+Long.toHexString(out[1])+
//          ";E0="+Long.toHexString(Eswap0)+
//          ";E1="+Long.toHexString(Eswap1)    ) ;

       long ll = out[0] ;
       
       byte [] b = new byte[9] ;
       
       b[0] = (byte) ( ll & 0xff ) ;
       b[1] = (byte) ((ll>> 8)&0xff) ;
       b[2] = (byte) ((ll>>16)&0xff) ;
       b[3] = (byte) ((ll>>24)&0xff) ;

       ll = out[1] ;

       
       b[4] = (byte) ( ll & 0xff ) ;
       b[5] = (byte) ((ll>> 8)&0xff) ;
       b[6] = (byte) ((ll>>16)&0xff) ;
       b[7] = (byte) ((ll>>24)&0xff) ;

       int  y = 0 , c ;
       long u = 0x80;
       b[8]   = 0 ;
       for( i = 2 ; i < 13 ; i ++){
	
	  c = 0 ;
	  for( int j = 0 ; j < 6; j++ ){
              c <<= 1 ;
              if( ( b[y] & u) != 0 ) {
                  c |= 1;
              }
              u >>= 1 ;
              if( u == 0 ){
                 y++;
                 u = 0x80 ;
              }
          }
	  buff[i] = (byte)cov_2char[c];
       }
       buff[13]= (byte)'\0';
       return buff ;
   }
/*
#define D_ENCRYPT(L,R,S)	\
	t=(R^(R>>16)); \
	u=(t&E0); \
	t=(t&E1); \
	u=(u^(u<<16))^R^s[S  ]; \
	t=(t^(t<<16))^R^s[S+1]; \
	t=(t>>4)|(t<<28); \
	L^=	SPtrans[1][(t    )&0x3f]| \
		SPtrans[3][(t>> 8)&0x3f]| \
		SPtrans[5][(t>>16)&0x3f]| \
		SPtrans[7][(t>>24)&0x3f]| \
		SPtrans[0][(u    )&0x3f]| \
		SPtrans[2][(u>> 8)&0x3f]| \
		SPtrans[4][(u>>16)&0x3f]| \
		SPtrans[6][(u>>24)&0x3f];
*/
   private void body( long [] out , long [] s , long  E0 , long   E1 ){
                      
      long l = 0 , r = 0 ,t , u  ;
      int  S ;
      
      for( int j = 0 ; j < 25 ; j++ ){
	 
         for( int i = 0 ; i < (ITERATIONS*2) ; i += 4 ){
	   
	     /* D_ENCRYPT(l,r,  i);	 1 */
             S = i ;
	     t=(r^(r>>>16)); 
	     u=(t&E0);
	     t=(t&E1);
	     u=(u^(u<<16))^r^s[S];
	     t=(t^(t<<16))^r^s[S+1]; 
	     t=(t>>>4)|(t<<28); 
	     l^=  SPtrans[1][(int)((t     )&0x3f)]|
		  SPtrans[3][(int)((t>>> 8)&0x3f)]| 
		  SPtrans[5][(int)((t>>>16)&0x3f)]| 
		  SPtrans[7][(int)((t>>>24)&0x3f)]| 
		  SPtrans[0][(int)((u     )&0x3f)]| 
		  SPtrans[2][(int)((u>>> 8)&0x3f)]| 
		  SPtrans[4][(int)((u>>>16)&0x3f)]| 
		  SPtrans[6][(int)((u>>>24)&0x3f)];
             
	     /* D_ENCRYPT(r,l,  i+2);	 */
             S = i+2 ;
	     t=(l^(l>>>16)); 
	     u=(t&E0);
	     t=(t&E1);
	     u=(u^(u<<16))^l^s[S];
	     t=(t^(t<<16))^l^s[S+1]; 
	     t=(t>>>4)|(t<<28); 
	     r^=  SPtrans[1][(int)((t     )&0x3f)]|
		  SPtrans[3][(int)((t>>> 8)&0x3f)]| 
		  SPtrans[5][(int)((t>>>16)&0x3f)]| 
		  SPtrans[7][(int)((t>>>24)&0x3f)]| 
		  SPtrans[0][(int)((u     )&0x3f)]| 
		  SPtrans[2][(int)((u>>> 8)&0x3f)]| 
		  SPtrans[4][(int)((u>>>16)&0x3f)]| 
		  SPtrans[6][(int)((u>>>24)&0x3f)];
         }
         t = l;
         l = r;
         r = t;
      }
      t=r;
      r=(l>>>1)|(l<<31);
      l=(t>>>1)|(t<<31);
      /* clear the top bits on machines with 8byte longs */
      l&=0xffffffffL;
      r&=0xffffffffL;
      
      t  = (( r >>> 1 ) ^ l ) & 0x55555555L ;
      l ^= t ;
      r ^= ( t << 1 ) ; 
      
      t  = (( l >>> 8 ) ^ r ) & 0x00ff00ffL ;
      r ^= t ;
      l ^= ( t << 8 ) ; 
      
      t  = (( r >>> 2 ) ^ l ) & 0x33333333L ;
      l ^= t ;
      r ^= ( t << 2 ) ; 
      
      t  = (( l >>> 16 ) ^ r ) & 0x0000ffffL ;
      r ^= t ;
      l ^= ( t << 16 ) ; 
      
      t  = (( r >>> 4 ) ^ l ) & 0x0f0f0f0fL ;
      l ^= t ;
      r ^= ( t << 4 ) ; 
      /*
	PERM_OP(r,l,t, 1,0x55555555);
	PERM_OP(l,r,t, 8,0x00ff00ff);
	PERM_OP(r,l,t, 2,0x33333333);
	PERM_OP(l,r,t,16,0x0000ffff);
	PERM_OP(r,l,t, 4,0x0f0f0f0f);
      */ 
      
      out[0] = l ;
      out[1] = r ;               
   }
/*
#define PERM_OP(a,b,t,n,m) ((t)=((((a)>>(n))^(b))&(m)),\
	(b)^=(t),\
	(a)^=((t)<<(n)))
*/
   public static void main( String [] args ){
      if( ( args.length < 2 ) || ( args[0].length() < 2 ) ){
         System.err.println( "Usage : ... <salt:2> <string>" ) ;
         System.exit(4);
      }
      Crypt crypt  = new Crypt() ;
      System.out.println( " result : "+crypt.crypt( args[0] , args[1] ) ) ;
   }
}

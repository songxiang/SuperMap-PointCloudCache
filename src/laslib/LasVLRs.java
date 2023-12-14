package laslib;

public class LasVLRs {
    public char reserved;
    public byte[] user_id = new byte[16]; // 16 bytes
    public char record_id;
    public char record_length_after_header;
    public byte[] description = new byte[32]; // 32 bytes
    public byte[] data;
}

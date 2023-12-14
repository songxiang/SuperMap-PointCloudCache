package laslib;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//char (1 byte)
//unsigned char (1 byte)
//short (2 bytes)
//unsigned short (2 bytes)
//long (4 bytes)
//unsigned long (4 bytes)
//long long (8 bytes)
//unsigned long long (8 bytes)
//float (4 byte IEEE floating point format)
//double (8 byte IEEE floating point format)
//string (a variable series of 1 byte characters, ASCII encoded, null-terminated)
public class LasHeader {
    private static final PrintStream stderr = System.err;

    public byte[] file_signature = new byte[4];                  // starts at byte   0
    public char file_source_ID;                      // starts at byte   4
    public char global_encoding;                     // starts at byte   6
    public int project_ID_GUID_data_1;              // starts at byte   8 (unsigned)
    public char project_ID_GUID_data_2;              // starts at byte  12
    public char project_ID_GUID_data_3;              // starts at byte  14
    public byte[] project_ID_GUID_data_4 = new byte[8];            // starts at byte  16
    public double x_scale_factor;
    public double y_scale_factor;
    public double z_scale_factor;
    public double x_offset;
    public double y_offset;
    public double z_offset;
    public byte version_major;                        // starts at byte  24
    public byte version_minor;                        // starts at byte  25
    public byte[] system_identifier = new byte[32];              // starts at byte  26
    public byte[] generating_software = new byte[32];            // starts at byte  58
    public char file_creation_day;                   // starts at byte  90
    public char file_creation_year;                  // starts at byte  92
    public char header_size;                         // starts at byte  94
    public int offset_to_point_data;                // starts at byte  96 (unsigned)
    public int number_of_variable_length_records;   // starts at byte 100 (unsigned)
    public byte point_data_format;                    // starts at byte 104
    public char point_data_record_length;            // starts at byte 105
    public int number_of_point_records;             // starts at byte 107 (unsigned)
    public int[] number_of_points_by_return = new int[5];       // starts at byte 111 (unsigned)
    public double max_x;
    public double min_x;
    public double max_y;
    public double min_y;
    public double max_z;
    public double min_z;

    // LAS 1.3 only
    public long start_of_waveform_data_packet_record; // unsigned

    // LAS 1.4 only
    public long start_of_first_extended_variable_length_record; // unsigned
    public int number_of_extended_variable_length_records; // unsigned
    public long extended_number_of_point_records; // unsigned
    public long[] extended_number_of_points_by_return = new long[15]; // unsigned

    public int user_data_in_header_size; // unsigned
    public byte[] user_data_in_header;
    public int user_data_after_header_size; // unsigned
    public byte[] user_data_after_header;

    public LasHeader() {
        file_signature[0] = 'L'; file_signature[1] = 'A'; file_signature[2] = 'S'; file_signature[3] = 'F';
        version_major = 1;
        version_minor = 2;
        header_size = 227;
        offset_to_point_data = 227;
        point_data_record_length = 20;
        x_scale_factor = 0.01;
        y_scale_factor = 0.01;
        z_scale_factor = 0.01;
    }
}

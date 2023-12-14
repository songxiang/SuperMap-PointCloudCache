import com.supermap.data.PrjCoordSys;
import com.supermap.data.PrjFileType;
import laslib.*;

import java.io.*;

import static java.lang.Double.longBitsToDouble;
import static utils.utils.fopenRAF;

public class ReadLas {
    private String lasFile;
    private LasHeader lasHeader = new LasHeader();
    private LasVLRs[] lasVLRs;

    public ReadLas(String _lasfile) throws IOException {
        lasFile = _lasfile;
        readLasHeader();
    }

    private void readLasHeader() throws IOException {
        RandomAccessFile file = fopenRAF(lasFile.toCharArray(), "rb");
        if (file == null) {
            System.out.println("ERROR: cannot open file '%s'\n" + lasFile);
            return;
        }
        ByteStreamIn stream = new ByteStreamInFile(file);
        /**
         * 读取header
         */
        try {
            stream.getBytes(lasHeader.file_signature, 4);
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.file_signature\n");

        }
        try {
            lasHeader.file_source_ID = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.file_source_ID\n");

        }
        try {
            lasHeader.global_encoding = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.global_encoding\n");

        }
        try {
            lasHeader.project_ID_GUID_data_1 = stream.get32bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.project_ID_GUID_data_1\n");

        }
        try {
            lasHeader.project_ID_GUID_data_2 = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.project_ID_GUID_data_2\n");

        }
        try {
            lasHeader.project_ID_GUID_data_3 = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.project_ID_GUID_data_3\n");

        }
        try {
            stream.getBytes(lasHeader.project_ID_GUID_data_4, 8);
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.project_ID_GUID_data_4\n");

        }
        try {
            lasHeader.version_major = stream.getByte();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.version_major\n");

        }
        try {
            lasHeader.version_minor = stream.getByte();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.version_minor\n");

        }
        try {
            stream.getBytes(lasHeader.system_identifier, 32);
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.system_identifier\n");

        }
        try {
            stream.getBytes(lasHeader.generating_software, 32);
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.generating_software\n");

        }
        try {
            lasHeader.file_creation_day = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.file_creation_day\n");

        }
        try {
            lasHeader.file_creation_year = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.file_creation_year\n");

        }
        try {
            lasHeader.header_size = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.header_size\n");

        }
        try {
            lasHeader.offset_to_point_data = stream.get32bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.offset_to_point_data\n");

        }
        try {
            lasHeader.number_of_variable_length_records = stream.get32bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.number_of_variable_length_records\n");

        }
        try {
            lasHeader.point_data_format = stream.getByte();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.point_data_format\n");

        }
        try {
            lasHeader.point_data_record_length = stream.get16bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.point_data_record_length\n");

        }
        try {
            lasHeader.number_of_point_records = stream.get32bitsLE();
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.number_of_point_records\n");

        }
        for (int i = 0; i < 5; i++) {
            try {
                lasHeader.number_of_points_by_return[i] = stream.get32bitsLE();
            } catch (Exception e) {
                System.out.println("ERROR: reading lasHeader.number_of_points_by_return %d\n" + i);

            }
        }
        try {
            lasHeader.x_scale_factor = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.x_scale_factor\n");

        }
        try {
            lasHeader.y_scale_factor = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.y_scale_factor\n");

        }
        try {
            lasHeader.z_scale_factor = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.z_scale_factor\n");

        }
        try {
            lasHeader.x_offset = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.x_offset\n");

        }
        try {
            lasHeader.y_offset = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.y_offset\n");

        }
        try {
            lasHeader.z_offset = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.z_offset\n");

        }
        try {
            lasHeader.max_x = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.max_x\n");

        }
        try {
            lasHeader.min_x = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.min_x\n");

        }
        try {
            lasHeader.max_y = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.max_y\n");

        }
        try {
            lasHeader.min_y = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.min_y\n");

        }
        try {
            lasHeader.max_z = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.max_z\n");

        }
        try {
            lasHeader.min_z = longBitsToDouble(stream.get64bitsLE());
        } catch (Exception e) {
            System.out.println("ERROR: reading lasHeader.min_z\n");

        }
        readLasVLRS(stream);
    }

    private void readLasVLRS(ByteStreamIn stream) throws IOException {
        /**
         * 读取可变长度
         */
        int vlrs_size = 0; // unsigned
        if (lasHeader.number_of_variable_length_records != 0) {
            lasVLRs = new LasVLRs[lasHeader.number_of_variable_length_records];
            for (int i = 0; i < lasHeader.number_of_variable_length_records; i++) {
                lasVLRs[i] = new LasVLRs();
                if (((int) lasHeader.offset_to_point_data - vlrs_size - lasHeader.header_size) < 54) {
                    System.out.println(String.format("WARNING: only %d bytes until point block after reading %d of %d lasVLRs. skipping remaining lasVLRs ...\n", (int) lasHeader.offset_to_point_data - vlrs_size - lasHeader.header_size, i, lasHeader.number_of_variable_length_records));
                    lasHeader.number_of_variable_length_records = i;
                    break;
                }

                // read variable length records variable after variable (to avoid alignment issues)

                try {
                    lasVLRs[i].reserved = stream.get16bitsLE();
                } catch (Exception e) {
                    System.out.println(String.format("ERROR: reading lasVLRs[%d].reserved\n", i));
                }

                try {
                    stream.getBytes(lasVLRs[i].user_id, 16);
                } catch (Exception e) {
                    System.out.println(String.format("ERROR: reading lasVLRs[%d].user_id\n", i));
                }
                try {
                    lasVLRs[i].record_id = stream.get16bitsLE();
                } catch (Exception e) {
                    System.out.println(String.format("ERROR: reading lasVLRs[%d].record_id\n", i));
                }
                try {
                    lasVLRs[i].record_length_after_header = stream.get16bitsLE();
                } catch (Exception e) {
                    System.out.println(String.format("ERROR: reading lasVLRs[%d].record_length_after_header\n", i));
                }
                try {
                    stream.getBytes(lasVLRs[i].description, 32);
                } catch (Exception e) {
                    System.out.println(String.format("ERROR: reading lasVLRs[%d].description\n", i));
                }

                // keep track on the number of bytes we have read so far

                vlrs_size += 54;
                if (((int) lasHeader.offset_to_point_data - vlrs_size - lasHeader.header_size) < lasVLRs[i].record_length_after_header) {
                    System.out.println(String.format("WARNING: only %d bytes until point block when trying to read %d bytes into lasVLRs[%d].data\n", (int) lasHeader.offset_to_point_data - vlrs_size - lasHeader.header_size, lasVLRs[i].record_length_after_header, i));
                    lasVLRs[i].record_length_after_header = (char) (lasHeader.offset_to_point_data - vlrs_size - lasHeader.header_size);
                }
                if (lasVLRs[0].record_length_after_header != 0) {
                    if (Cstring.strcmp(lasVLRs[i].user_id, "laszip encoded") == 0) {

                    } else if (((Cstring.strcmp(lasVLRs[i].user_id, "LAStools") == 0) && (lasVLRs[i].record_id == 10)) || (Cstring.strcmp(lasVLRs[i].user_id, "lastools tile") == 0)) {

                    } else if (((Cstring.strcmp(lasVLRs[i].user_id, "LAStools") == 0) && (lasVLRs[i].record_id == 20))) {

                    } else {
                        lasVLRs[i].data = new byte[lasVLRs[i].record_length_after_header];

                        try {
                            stream.getBytes(lasVLRs[i].data, lasVLRs[i].record_length_after_header);
                        } catch (Exception e) {
                            System.out.println(String.format("ERROR: reading %d bytes of data into lasVLRs[%d].data\n", lasVLRs[i].record_length_after_header, i));
                        }
                    }
                }
                vlrs_size += lasVLRs[i].record_length_after_header;
            }
        }

        stream.close();
    }

    public PrjCoordSys getPrjCoordSys() throws IOException {
        if (lasVLRs.length == 0) {
            return null;
        } else {
            PrjCoordSys prj = new PrjCoordSys();
            for (int i = 0; i < lasVLRs.length; i++) {
                String wkt = new String(lasVLRs[i].data, "utf-8");
                String wkttempfilepath = "wkt.prj";
                File wkttempfile = new File(wkttempfilepath);
                if (wkttempfile.exists()) {
                    wkttempfile.delete();
                }
                wkttempfile.createNewFile();
                FileWriter fileWritter = new FileWriter(wkttempfile.getName(), true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(wkt);
                bufferWritter.close();
                if (prj.fromFile(wkttempfilepath, PrjFileType.ESRI)) {
                    if (prj.getEPSGCode() != 0) {
                        break;
                    }
                }
                wkttempfile.delete();
            }
            return prj;
        }
    }
}

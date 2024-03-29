package com.global_task.FileReaders;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import com.global_task.StringParsers.Calculation;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class CompressFileReader extends FileReader {
    private Integer compressLevel = 4;

    public CompressFileReader(InterfaceFileReader fileReader) {
        super(fileReader);
    }

    public Integer getCompressLevel() {
        return compressLevel;
    }

    public void setCompressLevel(Integer compressLevel) {
        this.compressLevel = compressLevel;
    }

    public static void Compress(Integer _compressLevel, String inputFileName, String outputFileName) throws Exception {
        try {
            FileInputStream is = new FileInputStream(inputFileName);
            FileOutputStream os = new FileOutputStream(outputFileName);
            byte[] data = is.readAllBytes();
            ByteArrayOutputStream writer = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(writer, new Deflater(_compressLevel));
            dos.write(data);
            writer.close();
            dos.close();
            is.close();
            os.write(Base64.getEncoder().encode(writer.toByteArray()));
            os.close();
        } catch(Throwable e) {
            throw new Exception("Error in file compress. Check selected file, actions and try again.", e);
        }
    }

    public byte[] Decompress(String inputName) throws Exception {
        try {
            FileInputStream tempReader = new FileInputStream(inputName);
            byte[] inputBytes = Base64.getDecoder().decode(tempReader.readAllBytes());
            InputStream in = new ByteArrayInputStream(inputBytes);
            InflaterInputStream reader = new InflaterInputStream(in);
            ByteArrayOutputStream writer = new ByteArrayOutputStream(512);
            int temp;
            while((temp = reader.read()) != -1) {
                writer.write((byte)temp);
            }
            in.close();
            reader.close();
            writer.close();
            tempReader.close();
            return writer.toByteArray();
        } catch(Throwable e) {
            throw new Exception("Error in file decomress. Check selected file, actions and try again.", e);
        }
    }

    private byte[] Decompress(byte[] tempByte) throws Exception {
        try {
            InputStream in = new ByteArrayInputStream(tempByte);
            InflaterInputStream reader = new InflaterInputStream(in);
            ByteArrayOutputStream writer = new ByteArrayOutputStream(512);
            int temp;
            while((temp = reader.read()) != -1) {
                writer.write(temp);
            }
            in.close();
            reader.close();
            writer.close();
            return writer.toByteArray();
        } catch(Throwable e) {
            throw new Exception("Error in file decompress. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception {
        try {
            byte[] tempByte = Decompress(inputName);
            return reader.Transform(tempByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decomress. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> ReadResult() throws Exception {
        try {
            byte[] tempByte = Decompress(inputName);
            return reader.TransformResult(tempByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decomress. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception {
        try {
            byte[] decompressByte = Decompress(tempByte);
            return reader.Transform(decompressByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decomress. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> TransformResult(byte[] tempByte) throws Exception {
        try {
            byte[] decompressByte = Decompress(tempByte);
            return reader.TransformResult(decompressByte);
        } catch(Throwable e) {
            throw new Exception("Error in file decomress. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.Write(result, outputFileName);
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.WriteResult(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) {
        ArrayList<ArrayList<String>> calculated = new ArrayList<>();
        for(ArrayList<String> lines : readFile) {
            for(String line : lines) {
                ArrayList<String> calculatedLine = new ArrayList<>();
                calculatedLine.add(Calculation.CalculationOfLine(line));
                calculated.add(calculatedLine);
            }
        }
        return calculated;
    }
    
    @Override
    public void getResult(String outputFileName) throws Exception {
        ArrayList<ArrayList<String>> readFile = ReadResult();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        WriteResult(result, outputFileName);
    }
}

package util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import document.Artifact;
import document.ArtifactsCollection;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileIOUtil {
    public static void abort(String m) {
        System.err.println(m);
        System.err.flush();
        Thread.dumpStack();
        System.exit(1);
    }

    public static void initDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        } else if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                f.delete();
            }
        }
    }

    @Nullable
    public static String readFile(@NotNull String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return Charset.forName("UTF-8").decode(ByteBuffer.wrap(encoded)).toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeFile(@NotNull String input, String path) {
        Path outPath = Paths.get(path);
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(outPath, charset)) {
            writer.write(input, 0, input.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static List<String> readFileByLine(@NotNull String path) {
        if(!isFileExist(path))
            return null;

        List<String> list = new LinkedList<>();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str = null;
            while((str = bufferedReader.readLine()) != null)
            {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static void continueWriteFile(@NotNull String input, String path) {
        Path outPath = Paths.get(path);
        Charset charset = Charset.forName("UTF-8");
        File file = new File(path);

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(input);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isFileExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static ArtifactsCollection getCollections(String dirPath, String postfixName) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            abort("Artifacts directory doesn't exist");
        }
        if (!dirFile.isDirectory()) {
            abort("Artifacts path should be a directory");
        }
        ArtifactsCollection collections = new ArtifactsCollection();
        for (File f : dirFile.listFiles()) {
            if (f.getName().endsWith(postfixName)) {
                String id = f.getName().split(".txt")[0];
                Artifact artifact = new Artifact(id, FileIOUtil.readFile(f.getPath()));
                collections.put(id, artifact);
            }
        }
        return collections;
    }

    public static List<String> readFile2List(File file) {
        List<String> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String s;
            while ((s = br.readLine()) != null) {
                s = s.replaceAll("\n", "").trim();
                if (s.length() > 0)
                    res.add(s);
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static Set<String> getClassNameFromRTM(String rtmPath) {
        Set<String> result = new HashSet<>();

        readFileByLine(rtmPath).stream().forEach(line->{
            String className = line.split(" ")[1];
            if (className.contains("_jsp"))
                className = className.substring(0, className.indexOf("_jsp"));
            result.add(className);
        });
        return result;

    }

    public static Set<String> readEachFileLine(@NotNull String dirPath){
        Set<String> result = new HashSet<>();
        File dirFile = new File(dirPath);
        if (dirFile.exists()) {
            for (File f : dirFile.listFiles()) {
                result.addAll(readFileByLine(f.getPath()));
            }
        }
        return result;
    }


}

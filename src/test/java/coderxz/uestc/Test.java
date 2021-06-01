package coderxz.uestc;

import coderxz.uestc.dto.APFParams;

import java.io.*;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        APFParams apfParams = new APFParams();
        apfParams.setStart("(103.93066,31.32343)");
        apfParams.setEnd("(103.99006,31.31375)");
        apfParams.setObstacles(Arrays.asList("(103.9548644, 31.29281989)", "(103.9551422, 31.29254211)"));//[(103.9548644, 31.29281989), (103.9551422, 31.29254211)]
        apfParams.setEnemys(Arrays.asList("[(103.90542, 31.33254211)]"));

        String start = apfParams.getStart();
        String end = apfParams.getEnd();
        String obstacles = apfParams.getObstacles().toString().replace("[[","[").replace("]]","]");
        String enemys = apfParams.getEnemys().toString().replace("[[","[").replace("]]","]");


        String pyPath = "C:\\D-drive-37093\\PycharmWorkSpace\\apf_enemy\\RunTheProject.py"; //python文件路径
        //String pyPath = "C:\\D-drive-37093\\PycharmWorkSpace\\apf_enemy\\hello.py";
        String[] comm = new String[] { "C:\\ProgramData\\Anaconda3\\python.exe", pyPath, start,end,obstacles,enemys};  //设定命令行
        try {
            Process proc = Runtime.getRuntime().exec(comm);  //执行py文件
//            Thread oThread = printMessage(proc.getInputStream(), proc.getErrorStream());
//
//            oThread.join();
//            int status = proc.waitFor();
//            System.out.println("Script exit code is:"+status);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Thread printMessage(final InputStream out, final InputStream err)
    {
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                Reader outReader = new InputStreamReader(out);
                BufferedReader outBf = new BufferedReader(outReader);

                Reader errReader = new InputStreamReader(err);
                BufferedReader errBf = new BufferedReader(errReader);

                String outLine = null, errLine = null;
                try{
                    while ((outLine = outBf.readLine()) != null || (errLine = errBf.readLine()) != null)
                    {
                        if(outLine!=null)
                        {
                            System.out.print("[OUT]------>>"+ outLine);
                        }

                        if(errLine!=null)
                        {
                            System.out.print("[ERR]------>>"+ errLine);
                        }
                        outLine = errLine = null;
                    }

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }finally {

                    try {
                        outBf.close();
                        errBf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();

        return thread;
    }

}

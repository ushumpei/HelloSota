package jp.co.yamarobo;

import java.awt.Color;

import jp.vstone.RobotLib.CPlayWave;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotPose;
import jp.vstone.RobotLib.CRobotUtil;
import jp.vstone.RobotLib.CSotaMotion;

public class Main {
  private static CRobotMem memory;
  private static CSotaMotion motion;
  private static CRobotPose pose;

  public static void main(String[] args) {
    memory = new CRobotMem();
    motion = new CSotaMotion(memory);

    if (memory.Connect()) {
      motion.InitRobot_Sota();
      motion.ServoOn();

      CPlayWave.PlayWave("sound/hellowworld.wav");

      pose = new CRobotPose();
      pose.setLED_Sota(Color.GREEN, Color.GREEN, 255, Color.GREEN);
      motion.play(pose, 500);
      CRobotUtil.wait(500);

      pose = new CRobotPose();
      pose.setLED_Sota(Color.GREEN, Color.GREEN, 255, Color.GREEN);
      pose.SetPose(new Byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new Short[] { 0, 500, 100, -500, -100, 0, -300, 0 });
      motion.play(pose, 2000);
      CRobotUtil.wait(5000);

      // 収納しやすい状態にする
      pose = new CRobotPose();
      pose.setLED_Sota(Color.BLACK, Color.BLACK, 255, Color.BLACK);
      pose.SetPose(new Byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new Short[] { 0, 0, -1000, 0, 1000, 0, 50, 0 });
      motion.play(pose, 2000);
      CRobotUtil.wait(2000);

      motion.ServoOff();
    }
  }
}
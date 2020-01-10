package com.xzixi.util.sftp.client.component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.xzixi.util.sftp.client.exception.SftpClientException;
import com.xzixi.util.sftp.client.util.ByteUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author 薛凌康
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sftp {

    private ChannelSftp channelSftp;

    /**
     * 下载远程文件
     *
     * @param dir 文件目录
     * @param name 文件名
     * @return 文件字节数组
     */
    public byte[] download(String dir, String name) {
        if (!isExist(dir)) {
            throw new SftpClientException(String.format("目录(%s)不存在！", dir));
        }
        String absoluteFilePath = dir + "/" + name;
        if (!isExist(absoluteFilePath)) {
            throw new SftpClientException(String.format("文件(%s)不存在！", absoluteFilePath));
        }
        try {
            channelSftp.cd(dir);
            InputStream in = channelSftp.get(name);
            return ByteUtil.inputStreamToByteArray(in);
        } catch (SftpException e) {
            throw new SftpClientException("sftp下载文件出错", e);
        }
    }

    /**
     * 上传文件
     *
     * @param dir 远程目录
     * @param name 远程文件名
     * @param in 输入流
     */
    public void upload(String dir, String name, InputStream in) {
        try {
            mkdirs(dir);
            channelSftp.cd(dir);
            channelSftp.put(in, name);
        } catch (SftpException e) {
            throw new SftpClientException("sftp上传文件出错", e);
        }
    }

    /**
     * 删除文件
     *
     * @param dir 远程目录
     * @param name 远程文件名
     */
    public void delete(String dir, String name) {
        if (!isDir(dir)) {
            return;
        }
        String absoluteFilePath = dir + "/" + name;
        if (!isExist(absoluteFilePath)) {
            return;
        }
        try {
            channelSftp.cd(dir);
            channelSftp.rm(name);
        } catch (SftpException e) {
            throw new SftpClientException("sftp删除文件出错", e);
        }
    }

    /**
     * 递归创建目录
     *
     * @param dir 目录绝对路径
     */
    public void mkdirs(String dir) {
        String[] folders = dir.split("/");
        try {
            channelSftp.cd("/");
            for (String folder: folders) {
                if (folder.length()>0) {
                    try {
                        channelSftp.cd(folder);
                    } catch (Exception e) {
                        channelSftp.mkdir(folder);
                        channelSftp.cd(folder);
                    }
                }
            }
        } catch (SftpException e) {
            throw new SftpClientException("sftp创建目录出错", e);
        }
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param path 文件或目录路径
     * @return {@code true} 存在 {@code false} 不存在
     */
    private boolean isExist(String path) {
        try {
            channelSftp.lstat(path);
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 判断是否目录
     *
     * @param path 待判断的路径
     * @return {@code true} 是目录 {@code false} 不是目录
     */
    private boolean isDir(String path) {
        try {
            SftpATTRS attrs = channelSftp.lstat(path);
            return attrs.isDir();
        } catch (SftpException e) {
            return false;
        }
    }
}
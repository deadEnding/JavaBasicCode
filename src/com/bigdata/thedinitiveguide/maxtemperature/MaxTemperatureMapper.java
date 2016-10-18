package com.bigdata.thedinitiveguide.maxtemperature;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author: deadend
 * @date: 11:09 AM 7/7/16
 * @version: 1.0
 * @description: Mapper
 */


public class MaxTemperatureMapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String year = line.substring(1, 5);
        int airTemperature;

        if (line.charAt(5) == '+') {
            airTemperature = Integer.parseInt(line.substring(6, 10));
        } else {
            airTemperature = Integer.parseInt(line.substring(5, 10));
        }
        String quality = line.substring(10, 11);
        if (airTemperature != MISSING && quality.matches("[01459]")) {
            context.write(new Text(year), new IntWritable(airTemperature));
        }
    }
}

package zx.soft.naive.bayes.mapred;

import java.io.IOException;
import java.util.Vector;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 统计每个类别对应的词数，统计的单词数有重复，即：cate——>size(words)
 * 
 * @author wgybzb
 *
 */
public class TrainCateMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws InterruptedException, IOException {

		String[] words = value.toString().split("\\s+");
		Vector<String> cates = TrainsVector.tokenizeCates(words[0]);
		Vector<String> text = TrainsVector.tokenizeDoc(words);

		for (String cate : cates) {
			context.write(new Text(cate), new IntWritable(text.size()));
		}
	}

}

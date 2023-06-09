package com.ontariotechu.sofe3980U;

import java.io.File;

import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.clustering.DensityBasedSpatialClustering;
import net.sf.javaml.clustering.FarthestFirst;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;



/**
 * Main app to perform the various clustering algorithms
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {

        // Create file handler for the iris data file
        Dataset data = FileHandler.loadDataset(new File("IDM_Clustering/src/devtools/iris.data"), 4, ",");

        // KMeans Clustering
        Dataset[] kmClusters = new KMeans().cluster(data);
        PrintClusters(kmClusters);

        // Farthest First Clustering Algorithm 
        Dataset[] ffClusters = new FarthestFirst().cluster(data);
        PrintClusters(ffClusters);

        // Density Based Spatial Scanning Clustering Algorithm.
        Dataset[] dbsscClusters = new DensityBasedSpatialClustering().cluster(data);
        PrintClusters(dbsscClusters);


        // Cluster evaluation
        System.out.println("CLUSTER EVALUATION");
        System.out.println("K-Means: " + EvaluateCluster(kmClusters));
        System.out.println("Farthest First: " + EvaluateCluster(ffClusters));
        System.out.println("Density Based Clustering: " + EvaluateCluster(dbsscClusters));

    }

    /**
     * Method that takes clusters and prints them out alongside the cluster count
     * 
     * @param clusters - dataset array of clusters
     */
    private static void PrintClusters(Dataset[] clusters){
        System.out.println("Cluster count: " + clusters.length +"\n");

        int count = 1;
        for(Dataset set : clusters){
            System.out.println("=== Cluster " + count++ + "===");
            System.out.println(set + "\n");
        }
    }

    /**
     * Method to evaluate a cluster
     * 
     * @param clusters - dataset array of clusters
     * @return - a string representation of the score
     */
    private static String EvaluateCluster(Dataset[] clusters){
        ClusterEvaluation sse= new SumOfSquaredErrors();
        double score=sse.score(clusters);

        return Double.toString(score);
    }
}

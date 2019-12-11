using System;

using System.Collections.Generic;

using System.ComponentModel;

using System.Data;

using System.Drawing;

using System.Linq;

using System.Text;


using System.IO;

namespace KPO_Lab2_console
{
    class Program
    {
        static void Main(string[] args)
        {

            
                

                if (!File.Exists("Out1.txt"))

                    File.Create("Out1.txt").Close();

                if (!File.Exists("Out2.txt"))

                    File.Create("Out2.txt").Close();

                if (!File.Exists("Out3.txt"))

                    File.Create("Out3.txt").Close();
                for(; ; )
            {
                Console.WriteLine("Решение по модели Джелинского - Моранды : 1\nЛинейная аппроксимация исходных данных МНК: 2");

            }
            }

            private void bMethod1_Click(object sender, EventArgs e)

            {

            //    for (int i = 0; i < 4; i++) cOutput.Series[i].Points.Clear();

            //   cOutput.Series[1].Name = "Решение по модели Джелинского - Моранды";
            
                int[] mDeltaN=new int[7];
            for (int i = 0; i < 7; i++) do { Console.Write("Введит dN" + (i + 1)); }while(!int.TryParse(Console.ReadLine(),out mDeltaN[i]));
                double K = 0.00001;

                double N = 0;

                FileStream fs = new FileStream("Out1.txt", FileMode.Create, FileAccess.Write, FileShare.ReadWrite);

                StreamWriter file = new StreamWriter(fs, Encoding.Unicode);

                while (true)

                {

                    file.WriteLine("K = " + K.ToString()); //коэффициент пропорциональности

                    double[] sum = new double[6] { 0, 0, 0, 0, 0, 0 };

                    double exec = 0;

                    for (int i = 0; i < 7; i++)

                    {

                        sum[0] = sum[0] + Convert.ToDouble((mDeltaN[i] * (Math.Exp(-Convert.ToDouble(K) * (i + 1)))));

                        sum[1] = sum[1] + Convert.ToDouble(((i + 1) * (Math.Exp(-2 * Convert.ToDouble(K) * (i + 1)))));

                        sum[2] = sum[2] + Convert.ToDouble((Math.Pow(Math.Exp(-Convert.ToDouble(K) * (i + 1)), 2)));

                        sum[3] = sum[3] + Convert.ToDouble((((i + 1) * mDeltaN[i]) * (Math.Exp(-Convert.ToDouble(K) * (i + 1)))));

                    }

                    for (int i = 0; i < 4; i++)

                        sum[i] = Math.Round(Convert.ToDouble(sum[i]), 2);

                    if (sum[2] == 0)

                        sum[2] = 0.1;

                    exec = (sum[0] * sum[1]) / sum[2];

                    exec = Math.Round(Convert.ToDouble(exec), 2);

                    if ((sum[3] < exec + 0.00001) && (sum[3] > exec - 0.00001))

                    {

                        file.WriteLine(K.ToString());
                    Console.WriteLine("K=" + K.ToString());
                    //    tbK.Text = K.ToString();

                        for (int i = 0; i < 7; i++)

                        {

                            sum[4] = sum[4] + Convert.ToDouble((mDeltaN[i] * (i + 1)) * (Math.Exp(-Convert.ToDouble(K) * (i + 1))));

                            sum[5] = sum[5] + Convert.ToDouble((i + 1) * Math.Exp(-2 * Convert.ToDouble(K) * (i + 1)));

                        }

                        N = sum[4] / (K * sum[5]);
                    Console.WriteLine("N=" + N.ToString());
                   //     tbN0.Text = N.ToString();

                        break;

                    }

                    else

                    {

                        K = K + 0.00001;

                        continue;

                    }

                }

                double masDeltaNras;

                int[] masTfunc = new int[10] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

                double coefficient = N * K;

                double[] discrepancy = new double[7]; // Невязка

                double[] residualSq = new double[7]; // Квадрат невязки

            //    tbP.Clear();

                double sumResidualSq = 0;

                int count = 0;

                double summ = 0;

                double m = Math.Round(K * N, 2);

                int iter = 0;

                double P = 0;

                while (P < 0.999)

                {

                    iter++;

                    masDeltaNras = coefficient * Convert.ToDouble(Math.Exp(-Convert.ToDouble(K) * iter));

                    P = Math.Pow((1 - Math.Exp(-K * iter)), N);

                    if (iter < 7)

                    {

                        discrepancy[iter] = m * Math.Exp(-K * iter - 1);

                        residualSq[iter] = Convert.ToDouble(Math.Pow(Convert.ToDouble(discrepancy[iter]), 2));

                        sumResidualSq += residualSq[iter];

                        File.AppendAllText("Out3.txt", "Discrepancy[" + iter + "] = " + discrepancy[iter].ToString(), Encoding.Default);

                        File.AppendAllText("Out3.txt", Environment.NewLine, Encoding.Default);

                        residualSq[iter] = Math.Pow(discrepancy[iter], 2);

                        File.AppendAllText("Out3.txt", "ResidualSquare[" + iter + "] = " + residualSq[iter].ToString(), Encoding.Default);

                        File.AppendAllText("Out3.txt", Environment.NewLine, Encoding.Default);

                     //   cOutput.Series[2].Points.AddXY(iter + 1, discrepancy[iter]);
                    //TODO:сделать  вывод точек для графика
                      //  cOutput.Series[3].Points.AddXY(iter + 1, residualSq[iter]);

                    //    cOutput.Series[0].Points.AddXY(iter + 1, mDeltaN[iter]);

                    }
                Console.WriteLine("Сумма невязок: " + sumResidualSq.ToString());
                //    tbSum.Text = sumResidualSq.ToString();
                //TODO:сделать  вывод точек для графика
                //cOutput.Series[1].Points.AddXY(iter, masDeltaNras);

                summ += masDeltaNras;

                }

                count = Convert.ToInt32(Math.Truncate(N - summ));
            Console.WriteLine("Количество оставшихся ошибок:" + count.ToString());
                //tbCount.Text = count.ToString();

                Console.WriteLine( "Pn: t = " + (iter - 1) + " ; " + P.ToString());

            }

            private void bLineAproksimation_Click(object sender, EventArgs e)

            {

            //   for (int i = 0; i < 4; i++) cOutput.Series[i].Points.Clear();

            // cOutput.Series[1].Name = "Линейная аппроксимация исходных данных МНК";

            int[] mDeltaN = new int[7];
            for (int i = 0; i < 7; i++) do { Console.Write("Введит dN" + (i + 1)); } while (!int.TryParse(Console.ReadLine(), out mDeltaN[i]));

            double[] sum = new double[4] { 0, 0, 0, 0 };

                double a = 0;

                double b = 0;

                for (int i = 0; i < 7; i++)

                {

                    sum[0] = sum[0] + i + 1;

                    sum[1] = sum[1] + Convert.ToDouble(Math.Pow(i + 1, 2));

                    sum[2] = sum[2] + (i + 1) * mDeltaN[i];

                    sum[3] = sum[3] + mDeltaN[i];

                }

                a = (sum[1] * sum[3] - sum[0] * sum[2]) / (7 * sum[1] - sum[0] * sum[0]);

                b = (7 * sum[2] - sum[0] * sum[3]) / (7 * sum[1] - sum[0] * sum[0]);
                Console.WriteLine("a=" + a.ToString());
            //  tbA.Text = a.ToString();
            Console.WriteLine("b=" + b.ToString());
                //tbB.Text = b.ToString();

                double[] masDeltaNras = new double[10];

                double[] discrepancy = new double[7];

                double[] residualSq = new double[7];

                double sumResidualSq = 0;

                for (int i = 0; i < 10; i++)

                {

                    masDeltaNras[i] = a + b * (i + 1);

                    if (i < 7)

                    {

                        discrepancy[i] = mDeltaN[i] - masDeltaNras[i];

                        residualSq[i] = Convert.ToDouble(Math.Pow(Convert.ToDouble(discrepancy[i]), 2));

                        sumResidualSq += residualSq[i];

                        File.AppendAllText("Out2.txt", "Discrepancy[" + i + "] = " + discrepancy[i].ToString(), Encoding.Default);

                        File.AppendAllText("Out2.txt", Environment.NewLine, Encoding.Default);

                        residualSq[i] = Math.Pow(discrepancy[i], 2);

                        File.AppendAllText("Out2.txt", "ResidualSquare[" + i + "] = " + residualSq[i].ToString(), Encoding.Default);

                        File.AppendAllText("Out2.txt", Environment.NewLine, Encoding.Default);

                      //  cOutput.Series[2].Points.AddXY(i + 1, discrepancy[i] + 1);

                      //  cOutput.Series[3].Points.AddXY(i + 1, residualSq[i] + 1);

                      ///  cOutput.Series[0].Points.AddXY(i + 1, mDeltaN[i]);

                    }
                Console.WriteLine("Сумма неувязок=" + sumResidualSq.ToString());
                  //  tbSum.Text = sumResidualSq.ToString();

                   // cOutput.Series[1].Points.AddXY(i + 1, masDeltaNras[i]);

                }

            }

        }

    }

